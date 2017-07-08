package relic;

import com.sun.jna.Memory;
import android.util.Base64;

/**
*	Element in Z_p
 */
public class bn_t extends Memory {
	public bn_t() {
		super(Relic.SIZES.bn_st_size);
	}

	@Override public String toString() {
		byte[] bytes = new byte[Relic.SIZES.bn_st_size];
		Relic.INSTANCE.bn_write_bin(bytes, Relic.SIZES.ep_st_size, this);
		return Base64.encodeToString(bytes,Base64.DEFAULT);
	}
}