/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.camel;

import static org.junit.Assert.*;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AMQ2240Test {

    private static final Logger LOG = LoggerFactory.getLogger(AMQ2240Test.class);

    @Test
    public void testBadVMTransportOptionsJMSPrefix() throws Exception {

        try{
            final String vmUri = "vm://localhost?" +
                "jms.redeliveryPolicy.maximumRedeliveries=0&" +
                "jms.redeliveryPolicy.initialRedeliveryDelay=500&" +
                "jms.useAsyncSend=false&jms.sendTimeout=ABC&" +
                "jms.maxXXXXReconnectAttempts=1&jms.timeout=3000";

            LOG.info("creating context with bad URI: " + vmUri);
            ActiveMQComponent.activeMQComponent(vmUri);

            fail("Should have received an exception from the bad URI.");
        } catch(Exception e) {
            // Expected
        }
    }

    @Test
    public void testBadVMTransportOptionsBrokerPrefix() throws Exception {
        try{
            final String vmUri = "vm://localhost?" +
                "broker.XXX=foo&broker.persistent=XXX&broker.useJmx=false";

            LOG.info("creating context with bad URI: " + vmUri);
            ActiveMQComponent.activeMQComponent(vmUri).start();

            fail("Should have received an exception from the bad URI.");
        } catch(Exception e) {
            // Expected
        }
    }
}
