package com.markinster.dont.rename.util.jsf;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JsfExceptionHandler extends ExceptionHandlerWrapper {
	
	private static Log log = LogFactory.getLog(JsfExceptionHandler.class);
	
	private ExceptionHandler wrapped;		
	
	public JsfExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}
	
	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}
	
	@Override
	public void handle() throws FacesException {
		
		//pega um iterator com a fila de eventos que contem as exceções
		Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents().iterator();
		while (it.hasNext()) {
			
			//pega o proximo
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) it.next().getSource();
			
			//pega a exceção
			Throwable exception = context.getException();
			BusinessException businessException = getBusinessException(exception);
			
			boolean handled = false;
			try {
				// se a excessão for do tipo que eu quero tratar faco o tratamento de desejado
				if (exception instanceof ViewExpiredException) {
					handled = true;
					redirect("/home");
				} else if(businessException != null) {
					handled = true;
					log.error("ERRO de sistema: " + exception.getMessage(), exception);
					FacesUtil.addErrorMessage(businessException.getMessage());
				} else {
					handled = true;
					log.error("ERRO de sistema: " + exception.getMessage(), exception);					
					redirect("/errorPage");
				}
				
			} finally {
				exception.printStackTrace();
				if (handled)
					it.remove();
			}			
		}
		
		getWrapped().handle();
	}

	private BusinessException getBusinessException(Throwable exception) {
		if (exception instanceof BusinessException) {
			return (BusinessException) exception;
		} else if (exception.getCause() != null) {
			return getBusinessException(exception.getCause());
		}
			
		return null;
	}

	private void redirect(String page) {
		try {

			FacesContext currentInstance = FacesContext.getCurrentInstance();
			ExternalContext externalContext = currentInstance.getExternalContext();
			String contextPath = externalContext.getRequestContextPath();
		
			externalContext.redirect(contextPath + page);
			currentInstance.responseComplete();
			
		} catch (IOException e) {
			throw new FacesException(e);
		}
		
	}

}
