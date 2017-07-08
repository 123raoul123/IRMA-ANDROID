package relic;

import android.util.Base64;
import com.sun.jna.Memory;


/**
 * Element in G_1
 */
public class ep_t extends Memory {
	public ep_t() {
		super(Relic.SIZES.ep_st_size);
	}

	@Override public String toString() {
		byte[] bytes = new byte[Relic.SIZES.ep_st_size];
		Relic.INSTANCE.ep_write_bin(bytes, Relic.SIZES.ep_st_size, this, 0);
		return Base64.encodeToString(bytes,Base64.DEFAULT);
	}
}
