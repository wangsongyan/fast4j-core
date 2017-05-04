/**
 * 
 */
package cn.wangsy.fast4j.core.chinese;

/**
 * @author wangsy
 * @date 2017年5月4日下午1:38:04
 */
public class UnsupportedWordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnsupportedWordException() {
		super();
	}

	public UnsupportedWordException(String msg) {
		super(msg);
	}

	public UnsupportedWordException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public UnsupportedWordException(Throwable cause) {
		super(cause);
	}

}
