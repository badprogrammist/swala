/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.swala.web.filter;

import javax.faces.application.ResourceHandler;
import javax.servlet.http.HttpServletResponseWrapper;
import org.apache.myfaces.orchestra.conversation.ConversationRequestParameterProvider;

/**
 * @organization ITS360
 * @author Ildar Gafarov badprogrammist@gmail.com
 */

public class OrchestraParamControlResponseWrapper extends HttpServletResponseWrapper {

    
   public OrchestraParamControlResponseWrapper(HttpServletResponseWrapper httpServletResponse) {
      super(httpServletResponse);
   }

   @Override
   public String encodeURL(String url) {
      if (url.contains(ResourceHandler.RESOURCE_IDENTIFIER)) {
         boolean current = ConversationRequestParameterProvider.isInSeparationMode();
         /* Disable conversationContext parameter in current thread for the time of rendering link to a resource */
         ConversationRequestParameterProvider.setInSeparationMode(true);

         String result = super.encodeURL(url);

         /* Restore */
         ConversationRequestParameterProvider.setInSeparationMode(current);
         return result;
      }
      else return super.encodeURL(url);
   }
}
