package csheets.ext.macro_beanshell;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author José Barros
 */
public class CodeTest {

	public CodeTest() {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateCodeWithoutName() {
		Code code = new Code(null, BeanShell.NAME, "Hello", true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateCodeWithoutType() {
		Code code = new Code("test", null, "Hello", true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateCodeWithoutContent() {
		Code code = new Code("test", BeanShell.NAME, null, true);
	}

	@Test
	public void testCreateRightCode() {
		Code code = new Code("test", BeanShell.NAME, "Hello", true);
		Assert.assertNotNull(code);
		Assert.assertTrue(code != null);
	}

}
