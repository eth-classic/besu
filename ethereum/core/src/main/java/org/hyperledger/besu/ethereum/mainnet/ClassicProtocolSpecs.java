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

import org.hyperledger.besu.ethereum.core.Account;
<<<<<<< HEAD
import org.hyperledger.besu.ethereum.core.Address;
=======
import org.hyperledger.besu.ethereum.core.TransactionReceipt;
import org.hyperledger.besu.ethereum.core.Wei;
import org.hyperledger.besu.ethereum.core.WorldState;
>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27
import org.hyperledger.besu.ethereum.mainnet.contractvalidation.MaxCodeSizeRule;
import org.hyperledger.besu.ethereum.vm.MessageFrame;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Optional;
import java.util.OptionalInt;

<<<<<<< HEAD
import com.google.common.collect.ImmutableSet;

public class ClassicProtocolSpecs {

  public static final int SPURIOUS_DRAGON_CONTRACT_SIZE_LIMIT = 24576;

  private static final Address RIPEMD160_PRECOMPILE =
      Address.fromHexString("0x0000000000000000000000000000000000000003");

  // A consensus bug at Ethereum mainnet transaction 0xcf416c53
  // deleted an empty account even when the message execution scope
  // failed, but the transaction itself succeeded.
  private static final ImmutableSet<Address> SPURIOUS_DRAGON_FORCE_DELETE_WHEN_EMPTY_ADDRESSES =
      ImmutableSet.of(RIPEMD160_PRECOMPILE);
=======
public class ClassicProtocolSpecs {
  private static final Wei MAX_BLOCK_REWARD = Wei.fromEth(5);

  public static ProtocolSpecBuilder<Void> classicRecoveryInitDefinition(
      final OptionalInt contractSizeLimit, final OptionalInt configStackSizeLimit) {
    return MainnetProtocolSpecs.homesteadDefinition(contractSizeLimit, configStackSizeLimit)
        .blockHeaderValidatorBuilder(MainnetBlockHeaderValidator::createClassicValidator)
        .name("ClassicRecoveryInit");
  }
>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27

  public static ProtocolSpecBuilder<Void> tangerineWhistleDefinition(
      final Optional<BigInteger> chainId,
      final OptionalInt contractSizeLimit,
      final OptionalInt configStackSizeLimit) {
    return MainnetProtocolSpecs.homesteadDefinition(contractSizeLimit, configStackSizeLimit)
        .gasCalculator(TangerineWhistleGasCalculator::new)
        .transactionValidatorBuilder(
            gasCalculator -> new MainnetTransactionValidator(gasCalculator, true, chainId))
        .name("ClassicTangerineWhistle");
  }

  public static ProtocolSpecBuilder<Void> dieHardDefinition(
      final Optional<BigInteger> chainId,
      final OptionalInt configContractSizeLimit,
      final OptionalInt configStackSizeLimit) {
    return tangerineWhistleDefinition(chainId, OptionalInt.empty(), configStackSizeLimit)
<<<<<<< HEAD
        .gasCalculator(SpuriousDragonGasCalculator::new)
        .name("DieHard");
  }

=======
        .gasCalculator(DieHardGasCalculator::new)
        .difficultyCalculator(ClassicDifficultyCalculators.DIFFICULTY_BOMB_PAUSED)
        .name("DieHard");
  }

  public static ProtocolSpecBuilder<Void> gothamDefinition(
      final Optional<BigInteger> chainId,
      final OptionalInt contractSizeLimit,
      final OptionalInt configStackSizeLimit) {
    return dieHardDefinition(chainId, contractSizeLimit, configStackSizeLimit)
        .blockReward(MAX_BLOCK_REWARD)
        .difficultyCalculator(ClassicDifficultyCalculators.DIFFICULTY_BOMB_DELAYED)
        .blockProcessorBuilder(
            (transactionProcessor,
                transactionReceiptFactory,
                blockReward,
                miningBeneficiaryCalculator,
                skipZeroBlockRewards) ->
                new ClassicBlockProcessor(
                    transactionProcessor,
                    transactionReceiptFactory,
                    blockReward,
                    miningBeneficiaryCalculator,
                    skipZeroBlockRewards))
        .name("Gotham");
  }

>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27
  public static ProtocolSpecBuilder<Void> defuseDifficultyBombDefinition(
      final Optional<BigInteger> chainId,
      final OptionalInt contractSizeLimit,
      final OptionalInt configStackSizeLimit) {
<<<<<<< HEAD
    return MainnetProtocolSpecs.tangerineWhistleDefinition(contractSizeLimit, configStackSizeLimit)
        .difficultyCalculator(MainnetDifficultyCalculators.DIFFICULTY_BOMB_REMOVED)
=======
    return gothamDefinition(chainId, contractSizeLimit, configStackSizeLimit)
        .difficultyCalculator(ClassicDifficultyCalculators.DIFFICULTY_BOMB_REMOVED)
>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27
        .transactionValidatorBuilder(
            gasCalculator -> new MainnetTransactionValidator(gasCalculator, true, chainId))
        .name("DefuseDifficultyBomb");
  }

<<<<<<< HEAD
  // TODO edwardmack, this is just a place holder definiton, REPLACE with real definition
  public static ProtocolSpecBuilder<Void> atlantisDefinition(
      final Optional<BigInteger> chainId,
      final OptionalInt configContractSizeLimit,
      final OptionalInt configStackSizeLimit) {
    final int contractSizeLimit =
        configContractSizeLimit.orElse(SPURIOUS_DRAGON_CONTRACT_SIZE_LIMIT);
    final int stackSizeLimit = configStackSizeLimit.orElse(MessageFrame.DEFAULT_MAX_STACK_SIZE);

    return MainnetProtocolSpecs.tangerineWhistleDefinition(
            OptionalInt.empty(), configStackSizeLimit)
=======
  public static ProtocolSpecBuilder<Void> atlantisDefinition(
      final Optional<BigInteger> chainId,
      final OptionalInt configContractSizeLimit,
      final OptionalInt configStackSizeLimit,
      final boolean enableRevertReason) {
    final int contractSizeLimit =
        configContractSizeLimit.orElse(MainnetProtocolSpecs.SPURIOUS_DRAGON_CONTRACT_SIZE_LIMIT);
    final int stackSizeLimit = configStackSizeLimit.orElse(MessageFrame.DEFAULT_MAX_STACK_SIZE);
    return gothamDefinition(chainId, configContractSizeLimit, configStackSizeLimit)
        .evmBuilder(MainnetEvmRegistries::byzantium)
>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27
        .gasCalculator(SpuriousDragonGasCalculator::new)
        .skipZeroBlockRewards(true)
        .messageCallProcessorBuilder(
            (evm, precompileContractRegistry) ->
<<<<<<< HEAD
                new MainnetMessageCallProcessor(
                    evm,
                    precompileContractRegistry,
                    SPURIOUS_DRAGON_FORCE_DELETE_WHEN_EMPTY_ADDRESSES))
=======
                new MainnetMessageCallProcessor(evm, precompileContractRegistry))
        .precompileContractRegistryBuilder(MainnetPrecompiledContractRegistries::byzantium)
        .difficultyCalculator(ClassicDifficultyCalculators.EIP100)
        .transactionReceiptFactory(
            enableRevertReason
                ? ClassicProtocolSpecs::byzantiumTransactionReceiptFactoryWithReasonEnabled
                : ClassicProtocolSpecs::byzantiumTransactionReceiptFactory)
>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27
        .contractCreationProcessorBuilder(
            (gasCalculator, evm) ->
                new MainnetContractCreationProcessor(
                    gasCalculator,
                    evm,
                    true,
                    Collections.singletonList(MaxCodeSizeRule.of(contractSizeLimit)),
<<<<<<< HEAD
                    1,
                    SPURIOUS_DRAGON_FORCE_DELETE_WHEN_EMPTY_ADDRESSES))
        .transactionValidatorBuilder(
            gasCalculator -> new MainnetTransactionValidator(gasCalculator, true, chainId))
=======
                    1))
>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27
        .transactionProcessorBuilder(
            (gasCalculator,
                transactionValidator,
                contractCreationProcessor,
                messageCallProcessor) ->
                new MainnetTransactionProcessor(
                    gasCalculator,
                    transactionValidator,
                    contractCreationProcessor,
                    messageCallProcessor,
                    true,
                    stackSizeLimit,
                    Account.DEFAULT_VERSION))
        .name("Atlantis");
  }

<<<<<<< HEAD
  // TODO edwardmack, this is just a place holder definiton, REPLACE with real definition
  public static ProtocolSpecBuilder<Void> aghartaDefinition(
      final Optional<BigInteger> chainId,
      final OptionalInt configContractSizeLimit,
      final OptionalInt configStackSizeLimit,
      final boolean enableRevertReason) {
    final int contractSizeLimit =
        configContractSizeLimit.orElse(SPURIOUS_DRAGON_CONTRACT_SIZE_LIMIT);
    return MainnetProtocolSpecs.constantinopleFixDefinition(
            chainId, configContractSizeLimit, configStackSizeLimit, enableRevertReason)
        .gasCalculator(IstanbulGasCalculator::new)
        .precompileContractRegistryBuilder(MainnetPrecompiledContractRegistries::istanbul)
        .contractCreationProcessorBuilder(
            (gasCalculator, evm) ->
                new MainnetContractCreationProcessor(
                    gasCalculator,
                    evm,
                    true,
                    Collections.singletonList(MaxCodeSizeRule.of(contractSizeLimit)),
                    1,
                    SPURIOUS_DRAGON_FORCE_DELETE_WHEN_EMPTY_ADDRESSES))
        .name("Agharta");
=======
  private static TransactionReceipt byzantiumTransactionReceiptFactory(
      final TransactionProcessor.Result result, final WorldState worldState, final long gasUsed) {
    return new TransactionReceipt(
        result.isSuccessful() ? 1 : 0, gasUsed, result.getLogs(), Optional.empty());
  }

  private static TransactionReceipt byzantiumTransactionReceiptFactoryWithReasonEnabled(
      final TransactionProcessor.Result result, final WorldState worldState, final long gasUsed) {
    return new TransactionReceipt(
        result.isSuccessful() ? 1 : 0, gasUsed, result.getLogs(), result.getRevertReason());
>>>>>>> 9b9c373c88e4b662e81e83a516597e69d2e45b27
  }
}
