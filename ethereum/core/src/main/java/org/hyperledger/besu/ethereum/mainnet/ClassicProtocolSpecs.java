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
package org.hyperledger.besu.ethereum.mainnet;
import org.hyperledger.besu.ethereum.mainnet.contractvalidation.MaxCodeSizeRule;

import java.math.BigInteger;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Collections;

public class ClassicProtocolSpecs {

  public static final int SPURIOUS_DRAGON_CONTRACT_SIZE_LIMIT = 24576;

  public static ProtocolSpecBuilder<Void> ecip1015Definition(
      final Optional<BigInteger> chainId,
      final OptionalInt configContractSizeLimit,
      final OptionalInt configStackSizeLimit) {
    final int contractSizeLimit = configContractSizeLimit.orElse(MainnetProtocolSpecs.FRONTIER_CONTRACT_SIZE_LIMIT);

    return MainnetProtocolSpecs.homesteadDefinition(configContractSizeLimit, configStackSizeLimit)
        .gasCalculator(TangerineWhistleGasCalculator::new)
        .transactionValidatorBuilder(
            gasCalculator -> new MainnetTransactionValidator(gasCalculator, true, chainId))
        .contractCreationProcessorBuilder(
            (gasCalculator, evm) ->
                new MainnetContractCreationProcessor(
                    gasCalculator,
                    evm,
                    true,
                    Collections.singletonList(MaxCodeSizeRule.of(contractSizeLimit)),
                    0))
        .name("ClassicTangerineWhistle");
  }

  public static ProtocolSpecBuilder<Void> dieHardDefinition(
      final Optional<BigInteger> chainId,
      final OptionalInt configContractSizeLimit,
      final OptionalInt configStackSizeLimit) {


    return ecip1015Definition(chainId, OptionalInt.empty(), configStackSizeLimit)
        .gasCalculator(DieHardGasCalculator::new)
        .difficultyCalculator(ClassicDifficultyCalculators.DIFFICULTY_BOMB_DELAYED)
        .transactionValidatorBuilder(
            gasCalculator -> new MainnetTransactionValidator(gasCalculator, true, chainId))
        .name("DieHard");
  }

  public static ProtocolSpecBuilder<Void> defuseDifficultyBombDefinition(
      final Optional<BigInteger> chainId,
      final OptionalInt contractSizeLimit,
      final OptionalInt configStackSizeLimit) {
    return dieHardDefinition(chainId, contractSizeLimit, configStackSizeLimit)
        .difficultyCalculator(ClassicDifficultyCalculators.DIFFICULTY_BOMB_REMOVED)
        .transactionValidatorBuilder(
            gasCalculator -> new MainnetTransactionValidator(gasCalculator, true, chainId))
        .name("DefuseDifficultyBomb");
  }

  // TODO edwardmack, replace with real gotham ecip-1017 spec
  public static ProtocolSpecBuilder<Void> gothamDefinition(
      final Optional<BigInteger> chainId,
      final OptionalInt contractSizeLimit,
      final OptionalInt configStackSizeLimit) {
    return defuseDifficultyBombDefinition(chainId, contractSizeLimit, configStackSizeLimit)
        .name("Gotham");
  }

  // TODO edwardmack, this is just a place holder definiton, REPLACE with real definition
  public static ProtocolSpecBuilder<Void> atlantisDefinition(
      final Optional<BigInteger> chainId,
      final OptionalInt configContractSizeLimit,
      final OptionalInt configStackSizeLimit) {
    return gothamDefinition(chainId, OptionalInt.empty(), configStackSizeLimit).name("Atlantis");
  }

  // TODO edwardmack, this is just a place holder definiton, REPLACE with real definition
  public static ProtocolSpecBuilder<Void> aghartaDefinition(
      final Optional<BigInteger> chainId,
      final OptionalInt configContractSizeLimit,
      final OptionalInt configStackSizeLimit) {
    return atlantisDefinition(chainId, configContractSizeLimit, configStackSizeLimit)
        .name("Agharta");
  }
}
