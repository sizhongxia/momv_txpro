package org.tm.pro.web.data.source;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.tm.pro.web.data.holder.CustomerContextHolder;

public class CustomerRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return CustomerContextHolder.getCustomerType();
	}

}
