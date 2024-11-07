package com.ibm.example.cdi.internal;

import static org.osgi.service.component.annotations.ConfigurationPolicy.IGNORE;

import java.util.Set;

import io.openliberty.cdi.spi.CDIExtensionMetadata;
import org.osgi.service.component.annotations.Component;

// Javadoc for CDIExtensionMetadata can be found at: https://openliberty.io/docs/latest/reference/javadoc/spi/cdi-1.2.html
@Component(service = CDIExtensionMetadata.class, configurationPolicy = IGNORE)
public class CDIIntegrationMetaData implements CDIExtensionMetadata
{
	public Set<Class<?>> getBeanClasses() {
		return Set.of(CDIProducer.class);
	}

        /*
         * public Set<Class<? extends Annotation>> getBeanDefiningAnnotationClasses() {
         *    // Not used in this tutorial
         * }
         *
         * public default Set<Class<? extends Extension>> getExtensions() {
         *    // Not used in this tutorial
         * }
         */
}
