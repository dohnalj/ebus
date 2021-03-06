/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.csdev.ebus.cfg;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.csdev.ebus.cfg.std.EBusConfigurationReader;
import de.csdev.ebus.command.EBusCommandRegistry;
import de.csdev.ebus.command.EBusCommandUtils;
import de.csdev.ebus.command.IEBusCommandMethod;
import de.csdev.ebus.command.IEBusValue;
import de.csdev.ebus.command.datatypes.EBusTypeException;
import de.csdev.ebus.command.datatypes.IEBusType;
import de.csdev.ebus.utils.EBusUtils;

/**
 * @author Christian Sowada - Initial contribution
 *
 */
public class EBusNestedTemplatesTest {

    private static final Logger logger = LoggerFactory.getLogger(EBusNestedTemplatesTest.class);

    EBusCommandRegistry commandRegistry;

    @Before
    public void before() throws IOException, EBusConfigurationReaderException {
        commandRegistry = new EBusCommandRegistry(EBusConfigurationReader.class);
        commandRegistry.loadCommandCollection(
                EBusNestedTemplatesTest.class.getResource("/nested-templates/FirstTemplate.json"));
        commandRegistry.loadCommandCollection(
                EBusNestedTemplatesTest.class.getResource("/nested-templates/SecondTemplate.json"));
        commandRegistry.loadCommandCollection(
                EBusNestedTemplatesTest.class.getResource("/nested-templates/ThirdCommand.json"));
    }

    @Test
    public void testIdentification() {

        IEBusCommandMethod commandMethod = commandRegistry.getCommandMethodById("et", "test.nextest-block",
                IEBusCommandMethod.Method.SET);

        assertNotNull("Command et.test.nextest-blockn not found!", commandMethod);

        try {
            ByteBuffer buffer = EBusCommandUtils.buildMasterTelegram(commandMethod, (byte) 0x00, (byte) 0xFF, null);
            assertNotNull("Unable to compose byte buffer for command", buffer);

            logger.info(EBusUtils.toHexDumpString(buffer).toString());

        } catch (EBusTypeException e) {
            logger.error("error!", e);
        }

    }

    @Test
    public void testX() throws EBusTypeException {

        IEBusCommandMethod commandMethod = commandRegistry.getCommandMethodById("et", "test.tth",
                IEBusCommandMethod.Method.SET);

        assertNotNull("Command et.test.tth not found!", commandMethod);

        IEBusValue value = commandMethod.getMasterTypes().get(0);
        IEBusType<?> type = value.getType();

        @SuppressWarnings("unused")
        Object decode = type.decode(new byte[] { (byte) 0x90 });

        Map<String, Object> values = new HashMap<String, Object>();
        values.put("tth", new GregorianCalendar());

        try {
            ByteBuffer buffer = EBusCommandUtils.buildMasterTelegram(commandMethod, (byte) 0x00, (byte) 0xFF, values);
            assertNotNull("Unable to compose byte buffer for command", buffer);

            logger.info(EBusUtils.toHexDumpString(buffer).toString());

        } catch (EBusTypeException e) {
            logger.error("error!", e);
        }

    }

    @Test
    public void testY() throws EBusTypeException {

        IEBusCommandMethod commandMethod = commandRegistry.getCommandMethodById("et", "test.to",
                IEBusCommandMethod.Method.SET);

        assertNotNull("Command et.test.tth not found!", commandMethod);

        IEBusValue value = commandMethod.getMasterTypes().get(0);
        IEBusType<?> type = value.getType();

        @SuppressWarnings("unused")
        Object decode = type.decode(new byte[] { (byte) 0x90 });

        Map<String, Object> values = new HashMap<String, Object>();
        values.put("value", new GregorianCalendar());

        try {
            ByteBuffer buffer = EBusCommandUtils.buildMasterTelegram(commandMethod, (byte) 0x00, (byte) 0xFF, values);
            assertNotNull("Unable to compose byte buffer for command", buffer);

            logger.info(EBusUtils.toHexDumpString(buffer).toString());

        } catch (EBusTypeException e) {
            logger.error("error!", e);
        }

    }
}
