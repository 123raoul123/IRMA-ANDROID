package relic;

import Issue.IssueResponseMessage;
import Issue.IssueSignatureMessage;
import Issue.IssueRequestMessage;
import Issue.IssueCommitmentMessage;
import ShowCredential.ShowCredentialRequestMessage;
import ShowCredential.ShowCredentialCommitmentMessage;
import ShowCredential.ShowCredentialResponseMessage;
import irma.*;

import java.util.ArrayList;
import java.util.List;


public class HelloWorld {
	public HelloWorld() {
		initRelic();

		ep2_t Q = new ep2_t();
		Relic.INSTANCE.ep2_rand(Q);
		int n = 5;

		IssuerPrivateKey pk = new IssuerPrivateKey(n,Q);
		Issuer issuer = new Issuer(pk);
		Attributes at = new Attributes(n-1);
		UserPrivateKey privk = new UserPrivateKey();
		User user = new User(privk,at);

		// ISSUE PROTOCOL
		IssueRequestMessage fum_mes = user.createUserIssueFirstMessage();
		IssueResponseMessage fim_mes = issuer.createFirstIssuerMessage();
		IssueCommitmentMessage sum_mes = user.createUserIssueSecondMessage(fim_mes);
		IssueSignatureMessage sim_mes = issuer.createSecondIssuerMessage(fum_mes,sum_mes);
		user.setSignature(sim_mes);

		//ShowCredential protocol
		Verifier verifier = new Verifier(pk.getPublicKey());

		List<Boolean> bools = new ArrayList<>();
		bools.add(true);
		bools.add(false);
		bools.add(false);
		bools.add(true);

		ShowCredentialRequestMessage fium_mes = user.createShowCredentialRequestMessage(bools);
		ShowCredentialResponseMessage fvm_mes = verifier.createVerifierShowCredentialFirstMessage();
		ShowCredentialCommitmentMessage seum_mes = user.createShowCredentialCommitmentMessage(fium_mes, fvm_mes,bools);
		verifier.verifyCredentials(fium_mes,seum_mes);

		System.out.println("Cleaning up Relic");
		Relic.INSTANCE.core_clean();

	}

	private static void initRelic() {

        System.loadLibrary("jnidispatch");
		if(Relic.INSTANCE.core_init() == 1 || Relic.INSTANCE.ep_param_set_any_pairf() == 1) {
			Relic.INSTANCE.core_clean();
			System.exit(1);
		}
	}
}


