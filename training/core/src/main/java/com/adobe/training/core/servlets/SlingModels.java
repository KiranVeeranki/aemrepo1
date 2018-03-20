package com.adobe.training.core.servlets;

import java.io.IOException;
 
import javax.servlet.ServletException;
   
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.training.core.models.UserInfo;

import java.util.List;
import java.util.ArrayList;
import org.apache.sling.api.resource.Resource; 
import javax.jcr.Node; 
import java.util.Map;
import java.util.HashMap ; 
   
   
   
@SlingServlet(paths="/bin/slingmodel63", methods="GET")
public class SlingModels extends SlingAllMethodsServlet{
   
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Reference
    ResourceResolverFactory resolverFactory;    
    ResourceResolver resourceResolver;
     
     
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException,IOException{
        logger.info("inside sling model test servlet");
        response.setContentType("text/html");
         
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(ResourceResolverFactory.SUBSERVICE, "datawrite");
        ResourceResolver resolver = null;
           
        try {
                      
            //Invoke the adaptTo method to create a Session used to create a QueryManager
            resolver = resolverFactory.getServiceResourceResolver(param);
            Resource resource = resolver.getResource("/content/trainingproject/slingmodel");
            
                    
            ValueMap valueMap=resource.adaptTo(ValueMap.class);
             
            response.getWriter().write("Output from ValueMap is First Name: "+valueMap.get("firstName").toString()+" Last Name: "+valueMap.get("lastName").toString()+" Technology: "+valueMap.get("technology").toString()+"");
               
            UserInfo userInfo = resource.adaptTo(UserInfo.class);
            response.getWriter().write("Output from Sling Model is First Name: "+userInfo.getFirstName()+" Last Name: "+userInfo.getLastName()+" Technology: "+userInfo.getTechnology());
                       
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        finally{
             
            //if(resourceResolver.isLive())
                //resourceResolver.close();
        }
           
       
    }
   
}
