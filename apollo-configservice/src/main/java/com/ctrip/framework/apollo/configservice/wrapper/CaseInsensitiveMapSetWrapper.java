package com.ctrip.framework.apollo.configservice.wrapper;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
public class CaseInsensitiveMapSetWrapper {

	private final Map<String, Set<String>> delegate;

	public CaseInsensitiveMapSetWrapper(Map<String, Set<String>> delegate) {
		this.delegate = delegate;
	}

	public Set<String> get(String key) {
		return delegate.get(key.toLowerCase());
	}

	public Set<String> put(String key, String value) {
		Set<String> set = delegate.get(key.toLowerCase());
		if (null == set) {
			set = new HashSet<String>();
		}
		set.add(value);
		return delegate.put(key.toLowerCase(), set);
	}

	public Set<String> remove(String key, String value) {
		Set<String> set = delegate.get(key.toLowerCase());
		if (null != set) {
			if (set.contains(value)) {
				set.remove(value);
				delegate.put(key.toLowerCase(), set);
			}
		}
		return set;
	}

	public Set<String> remove(String key) {
		return delegate.remove(key.toLowerCase());
	}
}
