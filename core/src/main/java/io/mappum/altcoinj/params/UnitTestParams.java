/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.mappum.altcoinj.params;

import io.mappum.altcoinj.core.Block;
import io.mappum.altcoinj.core.NetworkParameters;
import io.mappum.altcoinj.core.Coin;
import io.mappum.altcoinj.pows.Sha256ProofOfWork;

import java.math.BigInteger;

/**
 * Network parameters used by the bitcoinj unit tests (and potentially your own). This lets you solve a block using
 * {@link io.mappum.altcoinj.core.Block#solve()} by setting difficulty to the easiest possible.
 */
public class UnitTestParams extends NetworkParameters {
    public UnitTestParams() {
        maxMoney = Coin.COIN.multiply(21000000);
        alertSigningKey = SATOSHI_KEY;
        genesisBlock = createGenesis(this, MainNetParams.GENESIS_INPUT, MainNetParams.GENESIS_SCRIPTPUBKEY);
        id = ID_UNITTESTNET;
        packetMagic = 0x0b110907;
        addressHeader = 111;
        p2shHeader = 196;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        proofOfWork = Sha256ProofOfWork.get();
        maxTarget = new BigInteger("00ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", 16);
        genesisBlock.setTime(System.currentTimeMillis() / 1000);
        genesisBlock.setDifficultyTarget(Block.EASIEST_DIFFICULTY_TARGET);
        genesisBlock.solve();
        port = 18333;
        interval = 10;
        dumpedPrivateKeyHeader = 239;
        targetTimespan = 200000000;  // 6 years. Just a very big number.
        spendableCoinbaseDepth = 5;
        subsidyDecreaseBlockCount = 100;
        dnsSeeds = null;
    }

    private static UnitTestParams instance;
    public static synchronized UnitTestParams get() {
        if (instance == null) {
            instance = new UnitTestParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return null;
    }
}
