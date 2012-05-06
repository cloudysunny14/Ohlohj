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
package ohlohj.apis;

import ohlohj.utils.StringUtils;

/**
 * 
 * @author cloudysunny14
 *
 */
public class OhlohUrlFactory {

	/**
	 * create request URL for Account.
	 * @param id
	 * @return the URL
	 */
	public static String createAccountUrl(long id) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/accounts/").append(id).append(".xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for account with email.
	 * @param email
	 * @return the URL
	 */
	public static String createAccountUrlWithEmail(String email) {
		StringBuilder sb = new StringBuilder();
        sb.append("http://www.ohloh.net/accounts/").append(StringUtils.calculateDigest(email)).append(".xml");
        return sb.toString();
	}
	
	/**
	 * create request URL for account with email.
	 * @return the URL
	 */
	public static String createAccountUrl() {
		StringBuilder sb = new StringBuilder();
        sb.append("http://www.ohloh.net/accounts.xml");
        return sb.toString();
	}
	
	/**
	 * create request URL for Project.
	 * @param id
	 * @return the URL
	 */
	public static String createProjectUrl(long id) {
		StringBuilder sb = new StringBuilder();
        sb.append("http://www.ohloh.net/projects/").append(id).append(".xml");
        return sb.toString();
	}
	
	/**
	 * create request URL for Project.
	 * @return the URL
	 */
	public static String createProjectUrl() {
		StringBuilder sb = new StringBuilder();
        sb.append("http://www.ohloh.net/projects.xml");
        return sb.toString();
	}
	
	/**
	 * create request URL for ActivityFact.
	 * @param id
	 * @param analysisId
	 * @return the URL
	 */
	public static String createActivityFactUrl(long id, long analysisId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/projects/").
		append(id).append("/analyses/").
		append(analysisId).
		append("/activity_facts.xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for ActivityFact.
	 * @param id
	 * @return the URL
	 */
	public static String createActivityFactUrl(long id) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/projects/")
		.append(id).append("/analyses/latest/activity_facts.xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for Analysis.
	 * @param projectId
	 * @return the URL
	 */
	public static String createAnalysisUrl(long projectId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/projects/")
		.append(projectId)
		.append("/analyses/latest.xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for Analysis.
	 * @param projectId
	 * @param analysisId
	 * @return the URL
	 */
	public static String createAnalysisUrl(long projectId, long analysisId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/projects/")
		.append(projectId)
		.append("/analyses/")
		.append(analysisId).append(".xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for ContributorFact.
	 * @param projectId
	 * @param contributorId
	 * @return the URL
	 */
	public static String createContributorFactUrl(long projectId, long contributorId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/projects/")
		.append(projectId)
		.append("/contributors/")
		.append(contributorId).append(".xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for ContributorFact.
	 * @param projectId
	 * @return the URL
	 */
	public static String createContributorFactUrl(long projectId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/projects/")
		.append(projectId)
		.append("/contributors.xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for Enlistments.
	 * @param projectId
	 * @return the URL
	 */
	public static String createEnlistmentsUrl(long projectId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/projects/")
		.append(projectId)
		.append("/enlistments.xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for Enlistments.
	 * @param projectId
	 * @param enlistMentId
	 * @return the URL
	 */
	public static String createEnlistmentUrl(long projectId, long enlistMentId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/projects/")
		.append(projectId)
		.append("/enlistments/")
		.append(enlistMentId).append(".xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for Factoid.
	 * @param projectId
	 * @return the URL
	 */
	public static String createFactoidUrl(long projectId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/projects/")
		.append(projectId)
		.append("/factoids.xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for Factoid.
	 * @param projectId
	 * @param factoid
	 * @return the URL
	 */
	public static String createFactoidUrl(long projectId, long factoid) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/projects/")
		.append(projectId)
		.append("/factoids/")
		.append(factoid).append(".xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for KudoSent.
	 * @param accountId
	 * @return the URL
	 */
	public static String createKudoSentUrl(long accountId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/accounts/")
		.append(accountId)
		.append("/kudos/sent.xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for Kudo.
	 * @param accountId
	 * @return the URL
	 */
	public static String createKudoUrl(long accountId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/accounts/")
		.append(accountId)
		.append("/kudos.xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for Language.
	 * @param id
	 * @return the URL
	 */
	public static String createLanguageUrl(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/languages/")
		.append(id).append(".xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for Language.
	 * @return the URL
	 */
	public static String createLanguageUrl() {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/languages.xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for SizeFact.
	 * @param projectId
	 * @return the URL
	 */
	public static String createSizeFactUrl(long projectId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/projects/")
		.append(projectId)
		.append("/analyses/latest/size_facts.xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for SizeFact.
	 * @param projectId
	 * @param analysisId
	 * @return the URL
	 */
	public static String createSizeFactUrl(long projectId, long analysisId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/projects/")
		.append(projectId)
		.append("/analyses/")
		.append(analysisId)
		.append("/size_facts.xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for Stack.
	 * @param accountId
	 * @param stackId
	 * @return the URL
	 */
	public static String createStackUrl(long accountId, long stackId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/accounts/")
		.append(accountId)
		.append("/stacks/")
		.append(stackId).append(".xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for Stack with AccountId.
	 * @param accountId
	 * @return the URL
	 */
	public static String createStackUrlWithAccountId(long accountId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/accounts/")
		.append(accountId)
		.append("/stacks/default.xml");
		return sb.toString();
	}
	
	/**
	 * create request URL for Stack.
	 * @param projectId
	 * @return the URL
	 */
	public static String createStackUrl(long projectId) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.ohloh.net/projects/")
		.append(projectId)
		.append("/stacks.xml");
		return sb.toString();
	}
}
