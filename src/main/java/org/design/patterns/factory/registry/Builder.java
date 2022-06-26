package org.design.patterns.factory.registry;

import org.design.patterns.factory.Factory;

public interface Builder<T> {

	void register(String label, Factory<T> factory);
}
