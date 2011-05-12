package com.thebuzzmedia.common.parser;

import java.io.IOException;

import com.thebuzzmedia.common.IToken;

public abstract class AbstractParser<IT, T> implements IParser<IT, T> {
	public static final int DEFAULT_BUFFER_REFILL_THRESHOLD = 1;

	static {
		if (BUFFER_SIZE < MIN_BUFFER_SIZE)
			throw new RuntimeException(
					"BUFFER_SIZE is too small. System property '"
							+ BUFFER_SIZE_PROPERTY_NAME
							+ "' is set to '"
							+ BUFFER_SIZE
							+ "' which is below the minimum threshold value of "
							+ MIN_BUFFER_SIZE + ".");
	}

	protected boolean running;

	protected int index;
	protected int bufferLength;
	protected int bufferRefillThreshold;

	protected T buffer;

	protected IT input;

	public void stop() throws UnsupportedOperationException {
		running = false;
	}

	public void reset() {
		running = true;

		index = -1;
		bufferLength = 0;
		bufferRefillThreshold = DEFAULT_BUFFER_REFILL_THRESHOLD;

		input = null;
	}

	public void parse(IT input, IParserCallback<IT, T> callback)
			throws IllegalArgumentException, IOException, ParserException {
		if (input == null)
			throw new IllegalArgumentException(
					"input cannot be null; it must be an open InputStream that is ready to be read from.");
		if (callback == null)
			throw new IllegalArgumentException("callback cannot be null");

		// Reset the parser state
		reset();

		// Run until input is empty or we are manually stopped.
		while (running) {
			// Refill the buffer if necessary
			if ((bufferLength - index) < bufferRefillThreshold)
				refillBuffer();

			// Invoke user-supplied parse logic
			IToken<T> token = parseImpl();

			// Pass generated token to the callback
			callback.tokenParsed(token, this);
		}
	}

	protected abstract int refillBuffer() throws IOException;

	protected abstract IToken<T> parseImpl() throws IOException,
			ParserException;
}