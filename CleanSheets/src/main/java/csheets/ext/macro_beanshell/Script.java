package csheets.ext.macro_beanshell;

/**
 *
 * @author Rui Bento
 */
public interface Script {

	public String getExample();

	public String run(String code);
}
