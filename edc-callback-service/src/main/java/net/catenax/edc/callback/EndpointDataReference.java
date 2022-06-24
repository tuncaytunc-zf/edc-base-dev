/*
 *  Copyright (c) 2020, 2021 Fraunhofer Institute for Software and Systems Engineering
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Amadeus - initial API and implementation
 *
 */

package net.catenax.edc.callback;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Describes an endpoint serving data.
 */
@JsonDeserialize(builder = EndpointDataReference.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EndpointDataReference {

    private final String id;
    private final String endpoint;
    private final String authKey;
    private final String authCode;
    private final Map<String, String> properties;

    private EndpointDataReference(String id, String endpoint, String authKey, String authCode, Map<String, String> properties) {
        this.id = id;
        this.endpoint = endpoint;
        this.authKey = authKey;
        this.authCode = authCode;
        this.properties = properties;
    }

    public String getId() {
        return id;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getAuthCode() {
        return authCode;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private String id = UUID.randomUUID().toString();
        private String endpoint;
        private String authKey;
        private String authCode;
        private final Map<String, String> properties = new HashMap<>();

        private Builder() {
        }

        @JsonCreator
        public static Builder newInstance() {
            return new Builder();
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder endpoint(String address) {
            this.endpoint = address;
            return this;
        }

        public Builder authKey(String authKey) {
            this.authKey = authKey;
            return this;
        }

        public Builder authCode(String authCode) {
            this.authCode = authCode;
            return this;
        }

        public Builder properties(Map<String, String> properties) {
            this.properties.putAll(properties);
            return this;
        }

        public EndpointDataReference build() {
            Objects.requireNonNull(endpoint, "endpoint");
            if (authKey != null) {
                Objects.requireNonNull(authCode, "authCode");
            }
            if (authCode != null) {
                Objects.requireNonNull(authKey, "authKey");
            }
            return new EndpointDataReference(id, endpoint, authKey, authCode, properties);
        }
    }
}
