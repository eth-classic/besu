/*
 * Copyright 2019 ConsenSys AG.
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
package org.hyperledger.besu.consensus.common;

import org.hyperledger.besu.crypto.SECP256K1.KeyPair;
import org.hyperledger.besu.ethereum.chain.Blockchain;
import org.hyperledger.besu.plugin.data.Address;
import org.hyperledger.besu.plugin.data.BlockHeader;
import org.hyperledger.besu.plugin.services.metrics.PoAMetricsService;
import org.hyperledger.besu.plugin.services.query.PoaQueryService;

import java.util.ArrayList;
import java.util.Collection;

public class PoaQueryServiceImpl implements PoaQueryService, PoAMetricsService {

  private final BlockInterface blockInterface;
  private final Blockchain blockchain;
  private final KeyPair localNodeKeypair;

<<<<<<< HEAD:consensus/common/src/main/java/org/hyperledger/besu/consensus/common/PoaQueryServiceImpl.java
  public PoaQueryServiceImpl(final BlockInterface blockInterface, final Blockchain blockchain) {
=======
  public PoaQueryServiceImpl(
      final BlockInterface blockInterface,
      final Blockchain blockchain,
      final KeyPair localNodeKeypair) {
>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27:consensus/common/src/main/java/org/hyperledger/besu/consensus/common/PoaQueryServiceImpl.java
    this.blockInterface = blockInterface;
    this.blockchain = blockchain;
    this.localNodeKeypair = localNodeKeypair;
  }

  @Override
  public Collection<Address> getValidatorsForLatestBlock() {
    return new ArrayList<>(blockInterface.validatorsInBlock(blockchain.getChainHeadHeader()));
  }

  @Override
  public Address getProposerOfBlock(final BlockHeader header) {
    return this.blockInterface.getProposerOfBlock(header);
  }

  protected Blockchain getBlockchain() {
    return blockchain;
  }
<<<<<<< HEAD:consensus/common/src/main/java/org/hyperledger/besu/consensus/common/PoaQueryServiceImpl.java
=======

  @Override
  public Address getLocalSignerAddress() {
    return org.hyperledger.besu.ethereum.core.Address.extract(localNodeKeypair.getPublicKey());
  }
>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27:consensus/common/src/main/java/org/hyperledger/besu/consensus/common/PoaQueryServiceImpl.java
}
