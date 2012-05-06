/*
 * Copyright 2012 cloudysunny14.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package ohlohj;

import static ohlohj.internal.http.RequestMethod.*;

import ohlohj.apis.CollectionParameter;
import ohlohj.apis.OhlohAPI;
import ohlohj.apis.OhlohUrlFactory;
import ohlohj.auth.OAuthAuthorization;
import ohlohj.conf.Configuration;
import ohlohj.internal.http.HttpRequest;

/**
 * 
 * @author cloudysunny14
 *
 */
public class OhlohAPIImpl extends OhlohAPIBase implements OhlohAPI, java.io.Serializable{

	private static final long serialVersionUID = -4401412348967436832L;

	/**
	 * Creates a OhlohAPI with the Configuration.
	 * @param conf 
	 * 		the Configuration
	 */
	public OhlohAPIImpl(Configuration conf) {
		super(conf);
	}
	
	 /**
     * Creates a OhlohAPI with the OAuth.
     * OAuthAuthorization is must be contains <br>
     * consumer key and consumer Secret provided by Ohloh.
     * @param oAuth
     * 		the OAuthAuthorization
     */
	public OhlohAPIImpl(OAuthAuthorization oAuth) {
		this(oAuth.getConfiguration());
		this.oAuth = oAuth;
	}
	
	/**
     * {@inheritDoc}
     */
	public Account createAccount(long id) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createAccountUrl(id)
				, keyParameter, oAuth);
		return factory.createAccount(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public Project createProject(long id) throws OhlohException {
        HttpRequest httpRequest = new HttpRequest(GET
        		, OhlohUrlFactory.createProjectUrl(id)
        		,keyParameter, oAuth);
        return factory.createProject(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public ResponseList<Project> createProject(CollectionParameter parameter) throws OhlohException {
        HttpRequest httpRequest = new HttpRequest(GET, OhlohUrlFactory.createProjectUrl(),
        		mergeParameters(parameter.getParameter(), keyParameter), oAuth);
        return factory.createProjectList(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public Account createAccountWithEmail(String email) throws OhlohException {
        HttpRequest httpRequest = new HttpRequest(GET
        		, OhlohUrlFactory.createAccountUrlWithEmail(email)
        		,keyParameter, oAuth);
        return factory.createAccount(http.request(httpRequest));
	}
	/**
     * {@inheritDoc} 
     */
	public ResponseList<Account> createAccountWithQuery(CollectionParameter parameter) throws OhlohException {
        HttpRequest httpRequest = new HttpRequest(GET
        		, OhlohUrlFactory.createAccountUrl()
        		,mergeParameters(parameter.getParameter(), keyParameter)
        		, oAuth);
        return factory.createAccountList(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public ResponseList<ActivityFact> createActivityFact(long id,
			long analysisId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createActivityFactUrl(id, analysisId)
				,keyParameter, oAuth);
		return factory.createActivityFactList(http.request(httpRequest));
	}
	/**
     * {@inheritDoc} 
     */
	public ResponseList<ActivityFact> createActivityFact(long id) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createActivityFactUrl(id)
				,keyParameter, oAuth);
		return factory.createActivityFactList(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public Analysis createAnalysis(long projectId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createAnalysisUrl(projectId)
				,keyParameter, oAuth);
		return factory.createAnalysis(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public Analysis createAnalysis(long projectId, long analysisId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createAnalysisUrl(projectId, analysisId)
				,keyParameter, oAuth);
		return factory.createAnalysis(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public ContributorFact createContributorFact(long projectId,
			long contributorId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createContributorFactUrl(projectId, contributorId)
				,keyParameter, oAuth);
		return factory.createContributorFact(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public ResponseList<ContributorFact> createContributorFact(long projectId
			, CollectionParameter parameter) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createContributorFactUrl(projectId)
				,mergeParameters(parameter.getParameter(), keyParameter), oAuth);
		return factory.createContributorFactList(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public ResponseList<EnlistMent> createEnlistMent(long projectId
			, CollectionParameter parameter) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createEnlistmentsUrl(projectId)
				,mergeParameters(parameter.getParameter(), keyParameter), oAuth);
		return factory.createEnlistMentList(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public EnlistMent createEnlistMent(long projectId, long enlistMentId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createEnlistmentUrl(projectId, enlistMentId)
				,keyParameter, oAuth);
		return factory.createEnlistMent(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public ResponseList<Factoid> createFactoid(long projectId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createFactoidUrl(projectId)
				,keyParameter, oAuth);
		return factory.createFactoidList(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public Factoid createFactoid(long projectId, long factoid) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createFactoidUrl(projectId, factoid)
				,keyParameter, oAuth);
		return factory.createFactoid(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public ResponseList<Kudo> createKudoSent(long accountId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createKudoSentUrl(accountId)
				,keyParameter, oAuth);
		return factory.createKudoList(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public ResponseList<Kudo> createKudo(long accountId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createKudoUrl(accountId)
				,keyParameter, oAuth);
		return factory.createKudoList(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public Languages createLanguages(int id) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createLanguageUrl(id)
				,keyParameter, oAuth);
		return factory.createLanguages(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public ResponseList<Languages> createLanguages(CollectionParameter parameter) throws OhlohException {
        HttpRequest httpRequest = new HttpRequest(GET
        		, OhlohUrlFactory.createLanguageUrl()
        		,mergeParameters(parameter.getParameter(), keyParameter), oAuth);
        return factory.createLanguagesList(http.request(httpRequest));
	}
	/**
     * {@inheritDoc} 
     */
	public ResponseList<SizeFact> createSizeFact(long projectId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createSizeFactUrl(projectId)
				,keyParameter, oAuth);
        return factory.createSizeFactList(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public ResponseList<SizeFact> createSizeFact(long projectId, long analysisId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createSizeFactUrl(projectId, analysisId)
				,keyParameter, oAuth);
		return factory.createSizeFactList(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public Stack createStack(long accountId, long stackId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createStackUrl(accountId, stackId)
				,keyParameter, oAuth);
		return factory.createStack(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public Stack createStack(long accountId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createStackUrlWithAccountId(accountId),
        		keyParameter, oAuth);
		return factory.createStack(http.request(httpRequest));
	}
	/**
     * {@inheritDoc}
     */
	public ResponseList<Stack> createStackWithCollectionParmeter(long projectId,
			CollectionParameter parameter) throws OhlohException {
        HttpRequest httpRequest = new HttpRequest(GET
        		, OhlohUrlFactory.createStackUrl(projectId)
        		,mergeParameters(parameter.getParameter(), keyParameter), oAuth);
        return factory.createStackList(http.request(httpRequest));
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        return true;
	}

	@Override
    public int hashCode() {
        return super.hashCode();
	}
	
	@Override
    public String toString() {
		return super.toString();
	}
}
