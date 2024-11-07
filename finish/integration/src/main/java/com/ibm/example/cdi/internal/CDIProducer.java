package com.ibm.example.cdi.internal;

import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;

import org.apache.commons.lang3.concurrent.ConstantInitializer;

import com.ibm.example.cdi.api.ExampleQualifier;

@ApplicationScoped
public class CDIProducer
{
	@Produces
	@Dependent
        @ExampleQualifier
	public ConstantInitializer<String> getConstantInitializer()
	{
            return new ConstantInitializer<String>("Hello");
        }
}
