package annas.graph.util.certifying;

public interface Verifier<I, O, C extends Certificate> {

	public boolean check(I input, O output, C certificate);
}
