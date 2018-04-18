package com.wuyuan.framework;

import com.wuyuan.framework.Bean.Data;
import com.wuyuan.framework.Bean.Handel;
import com.wuyuan.framework.Bean.Param;
import com.wuyuan.framework.Bean.View;
import com.wuyuan.framework.helper.BeanHelper;
import com.wuyuan.framework.helper.ConfigHelper;
import com.wuyuan.framework.helper.ControllerHelper;
import com.wuyuan.framework.util.CodecUtil;
import com.wuyuan.framework.util.JsonUtil;
import com.wuyuan.framework.util.ReflectionUtil;
import com.wuyuan.framework.util.StreamUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: kaiming.yin
 * @Description: 请求转发器
 * @Date: Created in 16:30 2018/4/17
 * @ModifedBy:
 * @since 1.0.0
 */

@WebServlet(urlPatterns = "/*",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化相关Helper类
        HelperLoader.init();

        ServletContext servletContext = config.getServletContext();

        //注册处理JSP的servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
        //注册处理静态资源的默认servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求方法与请求路径
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();
        //获取Action处理器
        Handel handel = ControllerHelper.getHandel(requestMethod,requestPath);
        //获取Bean实例
        Class<?> controllerClass = handel.getControllerClass();
        Object controllerBean = BeanHelper.getBean(controllerClass);
        //创建请求参数对象
        Map<String,Object> paramMap = new HashMap<String, Object>();
        Enumeration<String> paramNames = req.getParameterNames();
        while (paramNames.hasMoreElements()){
            String paramName = paramNames.nextElement();
            String paramValue = req.getParameter(paramName);
            paramMap.put(paramName,paramValue);
        }
        String body = CodecUtil.decodeURL((StreamUtil.getString(req.getInputStream())));
        if(StringUtils.isNotEmpty(body)){
            String[] params = StringUtils.split(body,"&");
            if(ArrayUtils.isNotEmpty(params)){
                for(String param : params){
                    String[] array = StringUtils.split(param,"=");
                    if(ArrayUtils.isNotEmpty(array)&&array.length==2){
                        String paramName = array[0];
                        String paramValue = array[1];
                        paramMap.put(paramName,paramValue);
                    }
                }
            }
        }
        Param param = new Param(paramMap);
        //调用Action方法
        Method actionMethod = handel.getActionMethod();
        Object result = ReflectionUtil.invokeMethod(controllerBean,actionMethod,param);
        //处理Action返回值
        if(result instanceof View){
            View view = (View)result;
            String path = view.getPath();
            if(StringUtils.isNotEmpty(path)){
                if(path.startsWith("/")){
                    resp.sendRedirect(req.getContextPath() + path);
                }else {
                    Map<String,Object> model = view.getModel();
                    for(Map.Entry<String,Object> modelEntry : model.entrySet()){
                        req.setAttribute(modelEntry.getKey(),modelEntry.getValue());
                    }
                    req.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(req,resp);
                }
            }
        }else if(result instanceof Data){
            //返回json数据
            Data data = (Data)result;
            Object model = data.getModel();
            resp.setContentType("application:json");
            resp.setCharacterEncoding("UTF-8");
            Writer writer = resp.getWriter();
            String  json = JsonUtil.toJson(model);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }
}
