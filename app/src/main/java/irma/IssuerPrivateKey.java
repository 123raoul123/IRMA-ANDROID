package irma;
import relic.*;
import java.util.ArrayList;
import java.util.List;

public class IssuerPrivateKey {
    private List<bn_t> aList = new ArrayList<>();
    private bn_t a,z;
    private IssuerPublicKey pubkey;


    public IssuerPrivateKey(int n, ep2_t Q)
    {
        this.a = new bn_t();
        this.z = new bn_t();

        bn_t ord = new bn_t();
        Relic.INSTANCE.ep_curve_get_ord(ord);
        Relic.INSTANCE.bn_rand_mod(this.a,ord);
        Relic.INSTANCE.bn_rand_mod(this.z,ord);

        for(int i =0;i<n;++i)
        {
            bn_t tmp = new bn_t();
            Relic.INSTANCE.bn_rand_mod(tmp,ord);
            this.aList.add(tmp);
        }

        //CREATE PUBLIC KEY
        ep2_t A = new ep2_t();
        ep2_t Z = new ep2_t();

        Relic.INSTANCE.ep2_mul_monty(A,Q,a);
        Relic.INSTANCE.ep2_mul_monty(Z,Q,z);
        List<ep2_t> A_list = new ArrayList<>();

        for(bn_t a_i: aList){
            ep2_t temp = new ep2_t();
            Relic.INSTANCE.ep2_mul_monty(temp,Q,a_i);
            A_list.add(temp);
        }

        this.pubkey = new IssuerPublicKey(A,Z,Q,A_list);
    }

    public IssuerPrivateKey(IssuerPrivateKey privkey)
    {
        this.a = privkey.geta();
        this.z = privkey.getz();
        this.aList = privkey.getaList();
        this.pubkey = privkey.getPublicKey();
    }

    public IssuerPublicKey getPublicKey()
    {
        return new IssuerPublicKey(this.pubkey);
    }

    public bn_t geta()
    {
        bn_t copy = new bn_t();
        Relic.INSTANCE.bn_copy(copy,this.a);
        return copy;
    }

    public bn_t getz()
    {
        bn_t copy = new bn_t();
        Relic.INSTANCE.bn_copy(copy,this.z);
        return copy;
    }

    public List<bn_t> getaList()
    {
        List<bn_t> copy = new ArrayList<>(aList);
        return copy;
    }

}
