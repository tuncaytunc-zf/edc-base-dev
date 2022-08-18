/*
 *  Copyright (c) 2022 Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Bayerische Motoren Werke Aktiengesellschaft (BMW AG) - initial API and implementation
 *
 */

package net.catenax.edc.backendservice;

import okhttp3.OkHttpClient;
import org.eclipse.dataspaceconnector.spi.WebService;
import org.eclipse.dataspaceconnector.spi.system.Inject;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;
import org.eclipse.dataspaceconnector.spi.types.TypeManager;

public class BackendServiceExtension implements ServiceExtension {

    @Inject
    private WebService webService;

    @Inject
    private OkHttpClient okHttpClient;

    @Inject
    private TypeManager typeManager;

    @Override
    public String name() {
        return "--- EDC Backend Service ---";
    }

    @Override
    public void initialize(ServiceExtensionContext context) {
        webService.registerResource(new EdcCallbackController(context.getMonitor(), okHttpClient));
        webService.registerResource(new ConsumerBackendServiceController(context.getMonitor()));
    }
}
