package com.prowidesoftware.swift.model.mt.mt0xx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.prowidesoftware.swift.model.field.Field103;
import com.prowidesoftware.swift.model.field.Field177;
import com.prowidesoftware.swift.model.field.Field243;

public class MT028Test {

	private String sample1 = "{1:F01VNDZBET2AXXX0000000000}{2:I028DYDYXXXXXXXXN}{4:"
			+ "{103:TGT}{243:1}}";
	
	private String sample2 = "{1:F01VNDZBET2AXXX0000000000}{2:I028DYDYXXXXXXXXN}{4:"
			+ "{103:TGT}{243:2}{177:0106051000}}";
	
	private String sample3 = "{1:F01VNDZBET2AXXX0000000000}{2:I028DYDYXXXXXXXXN}{4:"
			+ "{103:TGT}{243:1}{177:0106052200}{177:0106062359}}";
	
	@Test
	public void test_parse() {
		MT028 m = MT028.parse(sample1);
		assertNotNull(m);

		assertNotNull(m.getField103());
		assertEquals("TGT", m.getField103().getValue());
		
		assertNotNull(m.getField243());
		assertEquals("1", m.getField243().getValue());
		
		m = MT028.parse(sample2);
		
		assertNotNull(m.getField103());
		assertEquals("TGT", m.getField103().getValue());
		
		assertNotNull(m.getField243());
		assertEquals("2", m.getField243().getValue());
		
		assertNotNull(m.getField177());
		assertEquals("0106051000", m.getField177().get(0).getValue());
		
		m = MT028.parse(sample3);
		
		assertNotNull(m.getField103());
		assertEquals("TGT", m.getField103().getValue());
		
		assertNotNull(m.getField243());
		assertEquals("1", m.getField243().getValue());
		
		assertNotNull(m.getField177());
		assertEquals("0106052200", m.getField177().get(0).getValue());
		
		assertNotNull(m.getField177());
		assertEquals("0106062359", m.getField177().get(1).getValue());
	}
	
	@Test
	public void test_create() {
		MT028 m = new MT028();
		m.setSender("VNDZBET2AXXX");
		m.setReceiver("DYDYXXXXFXXX");
		m.append(new Field103("TGT"));
		m.append(new Field243("1"));
		
		assertEquals(sample1, m.message());
		
		m = new MT028();
		m.setSender("VNDZBET2AXXX");
		m.setReceiver("DYDYXXXXFXXX");
		m.append(new Field103("TGT"));
		m.append(new Field243("2"));
		m.append(new Field177("0106051000"));
		
		assertEquals(sample2, m.message());
		
		m = new MT028();
		m.setSender("VNDZBET2AXXX");
		m.setReceiver("DYDYXXXXFXXX");
		m.append(new Field103("TGT"));
		m.append(new Field243("1"));
		m.append(new Field177("0106052200"));
		m.append(new Field177("0106062359"));
		
		assertEquals(sample3, m.message());
	}
}
