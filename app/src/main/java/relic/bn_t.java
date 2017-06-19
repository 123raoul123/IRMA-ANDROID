package relic;

import android.util.Base64;

import com.sun.jna.Memory;


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