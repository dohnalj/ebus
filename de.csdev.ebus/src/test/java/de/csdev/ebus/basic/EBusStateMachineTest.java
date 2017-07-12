package de.csdev.ebus.basic;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.csdev.ebus.core.EBusDataException;
import de.csdev.ebus.core.EBusReceiveStateMachine;
import de.csdev.ebus.utils.EBusUtils;

public class EBusStateMachineTest {

	private static final Logger logger = LoggerFactory.getLogger(EBusStateMachineTest.class);

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void aa() throws EBusDataException {

		logger.info("Master/Slave - No Answer ...");
		
		thrown.expect(EBusDataException.class);
		
		x(EBusUtils.toByteArray("AA 30 08 50 22 03 CC 1A 27 59 AA"));
	}

	@Test
	public void bb() throws EBusDataException {

		logger.info("Master/Slave - NACK");

		thrown.expect(EBusDataException.class);

		x(EBusUtils.toByteArray("AA 30 08 50 22 03 CC 1A 27 59 FF AA"));
	}

	@Test
	public void xxx() throws EBusDataException {

		x(EBusUtils.toByteArray("AA 30 08 50 22 03 CC 1A 27 59 00 02 97 00 E2 00 AA"));

		x(EBusUtils.toByteArray("AA 30 FE 07 00 09 00 80 10 08 16 23 10 04 14 A2 AA"));

		x(EBusUtils.toByteArray("71 FE 50 18 0E 00 00 AE 02 07 00 A3 02 C3 01 02 00 00 00 7E AA"));

	}

	private void x(byte[] byteArray) throws EBusDataException {
		EBusReceiveStateMachine machine = new EBusReceiveStateMachine();

		for (byte b : byteArray) {
			machine.update(b);
		}
	}

}
