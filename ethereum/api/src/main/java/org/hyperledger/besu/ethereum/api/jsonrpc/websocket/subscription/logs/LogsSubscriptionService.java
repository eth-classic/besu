/*
 * Copyright ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package org.hyperledger.besu.ethereum.api.jsonrpc.websocket.subscription.logs;

import org.hyperledger.besu.ethereum.api.jsonrpc.internal.results.LogResult;
import org.hyperledger.besu.ethereum.api.jsonrpc.websocket.subscription.SubscriptionManager;
import org.hyperledger.besu.ethereum.api.jsonrpc.websocket.subscription.request.SubscriptionType;
<<<<<<< HEAD
import org.hyperledger.besu.ethereum.chain.BlockAddedEvent;
import org.hyperledger.besu.ethereum.chain.BlockAddedObserver;
import org.hyperledger.besu.ethereum.chain.Blockchain;

import java.util.List;
=======
import org.hyperledger.besu.ethereum.core.LogWithMetadata;

import java.util.function.Consumer;
>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27

public class LogsSubscriptionService implements Consumer<LogWithMetadata> {

  private final SubscriptionManager subscriptionManager;

  public LogsSubscriptionService(final SubscriptionManager subscriptionManager) {
    this.subscriptionManager = subscriptionManager;
  }

  @Override
<<<<<<< HEAD
  public void onBlockAdded(final BlockAddedEvent event, final Blockchain __) {
    final List<LogsSubscription> logsSubscriptions =
        subscriptionManager.subscriptionsOfType(SubscriptionType.LOGS, LogsSubscription.class);

    event
        .getLogsWithMetadata()
        .forEach(
            logWithMetadata ->
                logsSubscriptions.stream()
                    .filter(
                        logsSubscription ->
                            logsSubscription.getLogsQuery().matches(logWithMetadata))
                    .forEach(
                        logsSubscription ->
                            subscriptionManager.sendMessage(
                                logsSubscription.getSubscriptionId(),
                                new LogResult(logWithMetadata))));
=======
  public void accept(final LogWithMetadata logWithMetadata) {
    subscriptionManager.subscriptionsOfType(SubscriptionType.LOGS, LogsSubscription.class).stream()
        .filter(logsSubscription -> logsSubscription.getLogsQuery().matches(logWithMetadata))
        .forEach(
            logsSubscription ->
                subscriptionManager.sendMessage(
                    logsSubscription.getSubscriptionId(), new LogResult(logWithMetadata)));
>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27
  }
}
