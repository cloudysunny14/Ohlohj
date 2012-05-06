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
package ohlohj.appengine;

import static ohlohj.internal.http.RequestMethod.*;
import ohlohj.Account;
import ohlohj.ActivityFact;
import ohlohj.Analysis;
import ohlohj.ContributorFact;
import ohlohj.EnlistMent;
import ohlohj.Factoid;
import ohlohj.Kudo;
import ohlohj.Languages;
import ohlohj.OhlohAPIBase;
import ohlohj.OhlohException;
import ohlohj.Project;
import ohlohj.ResponseList;
import ohlohj.SizeFact;
import ohlohj.Stack;
import ohlohj.apis.CollectionParameter;
import ohlohj.apis.OhlohUrlFactory;
import ohlohj.appengine.internal.http.AsyncResponse;
import ohlohj.auth.OAuthAuthorization;
import ohlohj.conf.Configuration;
import ohlohj.internal.http.HttpRequest;
import ohlohj.internal.http.HttpResponse;

/**
 * @author cloudysunny14
 *
 */
public class FetchAsyncOhlohImpl extends OhlohAPIBase implements FetchAsyncOhloh, java.io.Serializable{

	private static final long serialVersionUID = -8834743709230683135L;

	/**
	 * Creates a OhlohAPI for asynchronous requests.
	 * @param conf
	 */
	public FetchAsyncOhlohImpl(Configuration conf) {
		super(conf);
	}
	
	 /**
     * Creates a OhlohAPI with the OAuth for asynchronous requests.
     * OAuthAuthorization is must be contains <br>
     * consumer key and consumer Secret provided by Ohloh.
     * @param oAuth
     * 		the OAuthAuthorization
     */
	public FetchAsyncOhlohImpl(OAuthAuthorization oAuth) {
		this(oAuth.getConfiguration());
		this.oAuth = oAuth;
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<Account> createAccount(long accountId)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET, OhlohUrlFactory.createAccountUrl(accountId), 
				keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<Account>() {
			public Account get() {
				try {
					return factory.createAccount(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<Account> createAccountWithEmail(String email)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
        		, OhlohUrlFactory.createAccountUrlWithEmail(email)
        		,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<Account>() {
			public Account get() {
				try {
					return factory.createAccount(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<ResponseList<Account>> createAccountWithQuery(
			CollectionParameter parameter) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
        		, OhlohUrlFactory.createAccountUrl()
        		,mergeParameters(parameter.getParameter(), keyParameter)
        		, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<ResponseList<Account>>() {
			public ResponseList<Account> get() {
				try {
					return factory.createAccountList(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<Project> createProject(long projectId)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
        		, OhlohUrlFactory.createProjectUrl(projectId)
        		,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<Project>() {
			public Project get() {
				try {
					return factory.createProject(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<ResponseList<Project>> createProject(
			CollectionParameter parameter) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET, OhlohUrlFactory.createProjectUrl(),
        		mergeParameters(parameter.getParameter(), keyParameter), oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<ResponseList<Project>>() {
			public ResponseList<Project> get() {
				try {
					return factory.createProjectList(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<ResponseList<ActivityFact>> createActivityFact(
			long projectId, long analysisId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createActivityFactUrl(projectId, analysisId)
				,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<ResponseList<ActivityFact>>() {
			public ResponseList<ActivityFact> get() {
				try {
					return factory.createActivityFactList(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<ResponseList<ActivityFact>> createActivityFact(
			long projectId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createActivityFactUrl(projectId)
				,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<ResponseList<ActivityFact>>() {
			public ResponseList<ActivityFact> get() {
				try {
					return factory.createActivityFactList(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<Analysis> createAnalysis(long projectId,
			long analysisId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createAnalysisUrl(projectId, analysisId)
				,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<Analysis>() {
			public Analysis get() {
				try {
					return factory.createAnalysis(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<Analysis> createAnalysis(long projectId)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createAnalysisUrl(projectId)
				,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<Analysis>() {
			public Analysis get() {
				try {
					return factory.createAnalysis(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<ResponseList<ContributorFact>> createContributorFact(
			long projectId, CollectionParameter parameter)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createContributorFactUrl(projectId)
				,mergeParameters(parameter.getParameter(), keyParameter), oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<ResponseList<ContributorFact>>() {
			public ResponseList<ContributorFact> get() {
				try {
					return factory.createContributorFactList(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<ContributorFact> createContributorFact(long projectId,
			long contributorId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createContributorFactUrl(projectId, contributorId)
				,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<ContributorFact>() {
			public ContributorFact get() {
				try {
					return factory.createContributorFact(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<EnlistMent> createEnlistMent(long projectId,
			long enlistMentId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createEnlistmentUrl(projectId, enlistMentId)
				,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<EnlistMent>() {
			public EnlistMent get() {
				try {
					return factory.createEnlistMent(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<ResponseList<EnlistMent>> createEnlistMent(
			long projectId, CollectionParameter parameter)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createEnlistmentsUrl(projectId)
				,mergeParameters(parameter.getParameter(), keyParameter), oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<ResponseList<EnlistMent>>() {
			public ResponseList<EnlistMent> get() {
				try {
					return factory.createEnlistMentList(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<Factoid> createFactoid(long projectId, long factoid)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createFactoidUrl(projectId, factoid)
				,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<Factoid>() {
			public Factoid get() {
				try {
					return factory.createFactoid(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<ResponseList<Factoid>> createFactoid(long projectId)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createFactoidUrl(projectId)
				,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<ResponseList<Factoid>>() {
			public ResponseList<Factoid> get() {
				try {
					return factory.createFactoidList(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<ResponseList<Kudo>> createKudoSent(long accountId)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createKudoSentUrl(accountId)
				,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<ResponseList<Kudo>>() {
			public ResponseList<Kudo> get() {
				try {
					return factory.createKudoList(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<ResponseList<Kudo>> createKudo(long accountId)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createKudoUrl(accountId)
				,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<ResponseList<Kudo>>() {
			public ResponseList<Kudo> get() {
				try {
					return factory.createKudoList(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<Languages> createLanguages(int id)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createLanguageUrl(id)
				,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<Languages>() {
			public Languages get() {
				try {
					return factory.createLanguages(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<ResponseList<Languages>> createLanguages(
			CollectionParameter parameter) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
        		, OhlohUrlFactory.createLanguageUrl()
        		,mergeParameters(parameter.getParameter(), keyParameter), oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<ResponseList<Languages>>() {
			public ResponseList<Languages> get() {
				try {
					return factory.createLanguagesList(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<ResponseList<SizeFact>> createSizeFact(long projectId,
			long analysisId) throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createSizeFactUrl(projectId, analysisId)
				,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<ResponseList<SizeFact>>() {
			public ResponseList<SizeFact> get() {
				try {
					return factory.createSizeFactList(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<ResponseList<SizeFact>> createSizeFact(long projectId)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createSizeFactUrl(projectId)
				,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<ResponseList<SizeFact>>() {
			public ResponseList<SizeFact> get() {
				try {
					return factory.createSizeFactList(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<Stack> createStack(long accountId, long stackId)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createStackUrl(accountId, stackId)
				,keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<Stack>() {
			public Stack get() {
				try {
					return factory.createStack(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<Stack> createStack(long accountId)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
				, OhlohUrlFactory.createStackUrlWithAccountId(accountId),
        		keyParameter, oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<Stack>() {
			public Stack get() {
				try {
					return factory.createStack(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
     * {@inheritDoc}
     */
	public AsyncResponse<ResponseList<Stack>> createStackWithCollectionParmeter(
			long projectId, CollectionParameter parameter)
			throws OhlohException {
		HttpRequest httpRequest = new HttpRequest(GET
        		, OhlohUrlFactory.createStackUrl(projectId)
        		,mergeParameters(parameter.getParameter(), keyParameter), oAuth);
		final HttpResponse response = http.request(httpRequest);
		return new AsyncResponse<ResponseList<Stack>>() {
			public ResponseList<Stack> get() {
				try {
					return factory.createStackList(response);
				} catch (OhlohException e) {
					throw new RuntimeException(e);
				}
			}
		};
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
