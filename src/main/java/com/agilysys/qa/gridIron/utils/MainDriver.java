package com.agilysys.qa.gridIron.utils;

import com.agilysys.insertanator.creates.populate.CreatePopulatedProperty;
import com.agilysys.insertanator.objectStores.MasterObject;
import com.agilysys.insertanator.objectStores.Tenant;
import com.agilysys.qa.constants.EndPoints;

/*
 * *Author - Harish Baskaran - 2018
 */
public class MainDriver {

	private static boolean Complete = false;

	static CreatePopulatedProperty createPopulatedProperty = new CreatePopulatedProperty();
	static MasterObject masterObject;

	public synchronized void OneTimeSetUp() {

		EndPoints.setEnvironmentUrl(Endpoints.getBasePMSUrl(), Endpoints.getBaseUrlPlatform());

		if (Complete == false) {
			Complete = true;
			masterObject = createPopulatedProperty.create();

		} else {
			while (null == masterObject) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}

	}

	public Tenant getTenant() {
		OneTimeSetUp();
		Tenant tenant = new Tenant(masterObject.getProperty().getTenantId(),
				masterObject.getTenant().getTenantCode());
		return tenant;
	}

	public MasterObject getMasterObject() {
		OneTimeSetUp();

		return masterObject;
	}
}
