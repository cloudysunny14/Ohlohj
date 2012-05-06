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

/**
 * 
 * @author cloudysunny14
 *
 */
public interface Languages {
	/**
	 * The unique ID of the language.
	 * @return the ID
	 */
	public int getId();
	/**
	 * A short, unique name for the language. Primarily for internal Ohloh use.
	 * @return the name
	 */
	public String getName();
	/**
	 * A human-friendly name for the language.
	 * @return the niceName
	 */
	public String getNiceName();
	/**
	 * Either code, markup, or build, which indicates that the language <br>
	 * is either a “standard” programming language, a document markup language <br>
	 * such as XML, or a build script such as a makefile.
	 * @return the category
	 */
	public String getCategory();
	/**
	 * The total net lines of code, excluding comments and blank lines, <br>
	 * written in this language across all projects on Ohloh.
	 * @return the code
	 */
	public long getCode();
	/**
	 * The total net comment lines written in this language across <br>
	 * all projects on Ohloh.
	 * @return the comments
	 */
	public long getComments();
	/**
	 * The total net blank lines in this language across all projects on Ohloh.
	 * @return the blanks
	 */
	public long getBlanks();
	/**
	 * A precomputed floating-point value for the percent of lines <br>
	 * in this language that are comments, across all projects on Ohloh.
	 * @return the commentRatio
	 */
	public float getCommentRatio();
	/**
	 * The total number of Projects on Ohloh which currently include <br>
	 * at least one line in this language.
	 * @return the projects
	 */
	public int getProjects();
	/**
	 * The total number of contributors on Ohloh who have written <br>
	 * at least one line of code in this language that still exists today.
	 * @return the contributors
	 */
	public int getContributors();
	/**
	 * The total number of commits on Ohloh which include <br>
	 * at least one line in this language.
	 * @return the commits
	 */
	public long getCommits();
}
