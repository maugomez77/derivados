package mx.com.libreria.math;

public class BlackAndScholes {
		   
	public static double BSCall(double So, double K, double r, double vol, double T) {
		double d1 = (Math.log(So/K) + ((r + Math.pow(vol, 2) / 2) * T)) / (Math.sqrt(T) * vol);
		double d2 = (Math.log(So/K) + ((r - Math.pow(vol, 2) / 2) * T)) / (Math.sqrt(T) * vol);
		
		double Nd1 = Gauss.PhiStandard(d1);
		double Nd2 = Gauss.PhiStandard(d2);
		
		return So * Nd1 - K * Math.exp(-r * T) * Nd2; 		
	}
	
	public static double BSPut(double So, double K, double r, double vol, double T) { 
		double d1 = (Math.log(So/K) + ((r + Math.pow(vol, 2) / 2) * T)) / (Math.sqrt(T) * vol);
		double d2 = (Math.log(So/K) + ((r - Math.pow(vol, 2) / 2) * T)) / (Math.sqrt(T) * vol);
		
		double Nd1 = Gauss.PhiStandard(-d1);
		double Nd2 = Gauss.PhiStandard(-d2);
		
		return K * Math.exp(-r * T) * Nd2 - So * Nd1;
	}

	public static void main(String[] args) { 
		
		//System.out.println("Resultado BS Call: " + BlackAndScholes.BSCall(52, 50, .12, .3, 0.25));
		
		//System.out.println("Resultado BS Put: " + BlackAndScholes.BSCall(50, 45, .05, .01, 0.25));
		
		//System.out.println("vola: " + BlackAndScholes.calculaVolatilidadImplicita(1000000, .00001, 7, 50, 45, .25, .05, .01));
		
		//System.out.println("Vol Implicita: " + VolatilidadImplicita.volatilidadImplicita(7, 50, 0.25, 45, .05, 0, .01));
	} 
}
