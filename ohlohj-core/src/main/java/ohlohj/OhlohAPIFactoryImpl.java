package ohlohj;

import ohlohj.internal.http.HttpResponse;

public class OhlohAPIFactoryImpl implements OhlohAPIFactory{

	public Account createAccount(HttpResponse response) throws OhlohException {
		return AccountImpl.createAccount(response);
	}

	public ResponseList<Account> createAccountList(HttpResponse response)
			throws OhlohException {
		return AccountImpl.createAccountList(response);
	}

	public Project createProject(HttpResponse response) throws OhlohException {
		return ProjectImpl.createProject(response);
	}

	public ResponseList<Project> createProjectList(HttpResponse response)
			throws OhlohException {
		return ProjectImpl.createProjectList(response);
	}

	public ResponseList<ActivityFact> createActivityFactList(
			HttpResponse response) throws OhlohException {
		return ActivityFactImpl.createActivityFactList(response);
	}

	public Analysis createAnalysis(HttpResponse response) throws OhlohException {
		return AnalysisImpl.createAnalysis(response);
	}

	public ContributorFact createContributorFact(HttpResponse response)
			throws OhlohException {
		return ContributorFactImpl.createContributorFact(response);
	}

	public ResponseList<ContributorFact> createContributorFactList(
			HttpResponse response) throws OhlohException {
		return ContributorFactImpl.createContributorFactList(response);
	}

	public ResponseList<EnlistMent> createEnlistMentList(HttpResponse response)
			throws OhlohException {
		return EnlistMentImpl.createEnlistMentList(response);
	}

	public EnlistMent createEnlistMent(HttpResponse response)
			throws OhlohException {
		return EnlistMentImpl.createEnlistMent(response);
	}

	public ResponseList<Factoid> createFactoidList(HttpResponse response)
			throws OhlohException {
		return FactoidImpl.createFactoidList(response);
	}

	public Factoid createFactoid(HttpResponse response) throws OhlohException {
		return FactoidImpl.createFactoid(response);
	}

	public ResponseList<Kudo> createKudoList(HttpResponse response)
			throws OhlohException {
		return KudoImpl.createKudoList(response);
	}

	public Languages createLanguages(HttpResponse response)
			throws OhlohException {
		return LanguagesImpl.createLanguages(response);
	}

	public ResponseList<Languages> createLanguagesList(HttpResponse response)
			throws OhlohException {
		 return LanguagesImpl.createLanguagesList(response);
	}

	public ResponseList<SizeFact> createSizeFactList(HttpResponse response)
			throws OhlohException {
		return SizeFactImpl.createSizeFactList(response);
	}

	public Stack createStack(HttpResponse response) throws OhlohException {
		return StackImpl.createStack(response);
	}

	public ResponseList<Stack> createStackList(HttpResponse response)
			throws OhlohException {
		return StackImpl.createStackList(response);
	}

}
