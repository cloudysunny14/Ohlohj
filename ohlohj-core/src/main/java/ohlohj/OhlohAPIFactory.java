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

import ohlohj.internal.http.HttpResponse;

/**
 * 
 * @author cloudysunny14
 *
 */
public interface OhlohAPIFactory {
	public Account createAccount(HttpResponse response) throws OhlohException;
	public ResponseList<Account> createAccountList(HttpResponse response) throws OhlohException;
	public Project createProject(HttpResponse response) throws OhlohException;
	public ResponseList<Project> createProjectList(HttpResponse response) throws OhlohException;
	public ResponseList<ActivityFact> createActivityFactList(HttpResponse response) throws OhlohException;
	public Analysis createAnalysis(HttpResponse response) throws OhlohException;
	public ContributorFact createContributorFact(HttpResponse response) throws OhlohException;
	public ResponseList<ContributorFact> createContributorFactList(HttpResponse response) throws OhlohException;
	public ResponseList<EnlistMent> createEnlistMentList(HttpResponse response) throws OhlohException;
	public EnlistMent createEnlistMent(HttpResponse response) throws OhlohException;
	public ResponseList<Factoid> createFactoidList(HttpResponse response) throws OhlohException;
	public Factoid createFactoid(HttpResponse response) throws OhlohException;
	public ResponseList<Kudo> createKudoList(HttpResponse response) throws OhlohException;
	public Languages createLanguages(HttpResponse response) throws OhlohException;
	public ResponseList<Languages> createLanguagesList(HttpResponse response) throws OhlohException;
	public ResponseList<SizeFact> createSizeFactList(HttpResponse response) throws OhlohException;
	public Stack createStack(HttpResponse response) throws OhlohException;
	public ResponseList<Stack> createStackList(HttpResponse response) throws OhlohException;
}
