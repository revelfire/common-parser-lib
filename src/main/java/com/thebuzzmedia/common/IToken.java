/**   
 * Copyright 2011 The Buzz Media, LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.thebuzzmedia.common;

/**
 * 
 * @author Riyad Kalla (software@thebuzzmedia.com)
 * 
 * @param <T>
 *            The type of the <code>source</code> this token is used to
 *            describe.
 */
public interface IToken<T> {
	public T getValue();

	public T getSource();

	public int getIndex();

	public int getLength();
}