package com.game.common.client;

import com.google.protobuf.GeneratedMessage;

public interface IClientProcessor {
	void process(Client client, int msgType, GeneratedMessage msg);
}
