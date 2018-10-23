package mx.com.libreria.math;

public class Counter {

	private Integer count;

	public Counter(Integer count) {
		this.count = count;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return ++count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
}
