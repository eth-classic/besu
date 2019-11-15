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
package org.hyperledger.besu.controller;

import org.hyperledger.besu.consensus.clique.CliqueBlockInterface;
import org.hyperledger.besu.consensus.common.BlockInterface;
import org.hyperledger.besu.consensus.common.PoaQueryServiceImpl;
<<<<<<< HEAD
=======
import org.hyperledger.besu.crypto.SECP256K1.KeyPair;
>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27
import org.hyperledger.besu.ethereum.chain.Blockchain;
import org.hyperledger.besu.plugin.services.metrics.PoAMetricsService;
import org.hyperledger.besu.plugin.services.query.PoaQueryService;
import org.hyperledger.besu.services.BesuPluginContextImpl;

public class CliqueQueryPluginServiceFactory implements PluginServiceFactory {

<<<<<<< HEAD
  final Blockchain blockchain;

  public CliqueQueryPluginServiceFactory(final Blockchain blockchain) {
    this.blockchain = blockchain;
=======
  private final Blockchain blockchain;
  private final KeyPair localNodeKeypair;

  public CliqueQueryPluginServiceFactory(
      final Blockchain blockchain, final KeyPair localNodeKeypair) {
    this.blockchain = blockchain;
    this.localNodeKeypair = localNodeKeypair;
>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27
  }

  @Override
  public void appendPluginServices(final BesuPluginContextImpl besuContext) {
    final BlockInterface blockInterface = new CliqueBlockInterface();
<<<<<<< HEAD

    final PoaQueryServiceImpl service = new PoaQueryServiceImpl(blockInterface, blockchain);
=======
    final PoaQueryServiceImpl service =
        new PoaQueryServiceImpl(blockInterface, blockchain, localNodeKeypair);
>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27
    besuContext.addService(PoaQueryService.class, service);
    besuContext.addService(PoAMetricsService.class, service);
  }
}
