package me.rahimklaber.stellar.base.xdr

/**
 * struct SCPStatement
 * {
 *     NodeID nodeID;    // v
 *     uint64 slotIndex; // i
 *
 *     union switch (SCPStatementType type)
 *     {
 *     case SCP_ST_PREPARE:
 *         struct
 *         {
 *             Hash quorumSetHash;       // D
 *             SCPBallot ballot;         // b
 *             SCPBallot* prepared;      // p
 *             SCPBallot* preparedPrime; // p'
 *             uint32 nC;                // c.n
 *             uint32 nH;                // h.n
 *         } prepare;
 *     case SCP_ST_CONFIRM:
 *         struct
 *         {
 *             SCPBallot ballot;   // b
 *             uint32 nPrepared;   // p.n
 *             uint32 nCommit;     // c.n
 *             uint32 nH;          // h.n
 *             Hash quorumSetHash; // D
 *         } confirm;
 *     case SCP_ST_EXTERNALIZE:
 *         struct
 *         {
 *             SCPBallot commit;         // c
 *             uint32 nH;                // h.n
 *             Hash commitQuorumSetHash; // D used before EXTERNALIZE
 *         } externalize;
 *     case SCP_ST_NOMINATE:
 *         SCPNomination nominate;
 *     }
 *     pledges;
 * };
 */
data class SCPStatement(
    val nodeID: NodeID,
    val slotIndex: ULong,
    val pledges: Pledges,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        nodeID.encode(stream)
        stream.writeLong(slotIndex.toLong())
        pledges.encode(stream)
    }

    sealed class Pledges(
        val type: SCPStatementType,
    ) : XdrElement {
        override fun encode(stream: XdrStream) {
            type.encode(stream)
        }

        data class Prepare(
            val quorumSetHash: Hash,
            val ballot: SCPBallot,
            val prepared: SCPBallot?,
            val preparedPrime: SCPBallot?,
            val nC: UInt,
            val nH: UInt,
        ) : XdrElement {
            override fun encode(stream: XdrStream) {
                quorumSetHash.encode(stream)
                ballot.encode(stream)
                prepared.encodeNullable(stream)
                preparedPrime.encodeNullable(stream)
                stream.writeInt(nC.toInt())
                stream.writeInt(nH.toInt())
            }

            companion object : XdrElementDecoder<Prepare> {
                override fun decode(stream: XdrStream): Prepare {
                    val quorumSetHash = Hash.decode(stream)
                    val ballot = SCPBallot.decode(stream)
                    val prepared = SCPBallot.decodeNullable(stream)
                    val preparedPrime = SCPBallot.decodeNullable(stream)
                    val nC = stream.readInt().toUInt()
                    val nH = stream.readInt().toUInt()
                    return Prepare(quorumSetHash, ballot, prepared, preparedPrime, nC, nH)
                }
            }
        }

        data class StPrepare(
            val prepare: Prepare,
        ) : Pledges(SCPStatementType.SCP_ST_PREPARE) {
            override fun encode(stream: XdrStream) {
                super.encode(stream)
                prepare.encode(stream)
            }
        }

        data class Confirm(
            val ballot: SCPBallot,
            val nPrepared: UInt,
            val nCommit: UInt,
            val nH: UInt,
            val quorumSetHash: Hash,
        ) : XdrElement {
            override fun encode(stream: XdrStream) {
                ballot.encode(stream)
                stream.writeInt(nPrepared.toInt())
                stream.writeInt(nCommit.toInt())
                stream.writeInt(nH.toInt())
                quorumSetHash.encode(stream)
            }

            companion object : XdrElementDecoder<Confirm> {
                override fun decode(stream: XdrStream): Confirm {
                    val ballot = SCPBallot.decode(stream)
                    val nPrepared = stream.readInt().toUInt()
                    val nCommit = stream.readInt().toUInt()
                    val nH = stream.readInt().toUInt()
                    val quorumSetHash = Hash.decode(stream)
                    return Confirm(ballot, nPrepared, nCommit, nH, quorumSetHash)
                }
            }
        }

        data class StConfirm(
            val confirm: Confirm,
        ) : Pledges(SCPStatementType.SCP_ST_CONFIRM) {
            override fun encode(stream: XdrStream) {
                super.encode(stream)
                confirm.encode(stream)
            }
        }

        data class Externalize(
            val commit: SCPBallot,
            val nH: UInt,
            val commitQuorumSetHash: Hash,
        ) : XdrElement {
            override fun encode(stream: XdrStream) {
                commit.encode(stream)
                stream.writeInt(nH.toInt())
                commitQuorumSetHash.encode(stream)
            }

            companion object : XdrElementDecoder<Externalize> {
                override fun decode(stream: XdrStream): Externalize {
                    val commit = SCPBallot.decode(stream)
                    val nH = stream.readInt().toUInt()
                    val commitQuorumSetHash = Hash.decode(stream)
                    return Externalize(commit, nH, commitQuorumSetHash)
                }
            }
        }

        data class StExternalize(
            val externalize: Externalize,
        ) : Pledges(SCPStatementType.SCP_ST_EXTERNALIZE) {
            override fun encode(stream: XdrStream) {
                super.encode(stream)
                externalize.encode(stream)
            }
        }

        data class StNominate(
            val nominate: SCPNomination,
        ) : Pledges(SCPStatementType.SCP_ST_NOMINATE) {
            override fun encode(stream: XdrStream) {
                super.encode(stream)
                nominate.encode(stream)
            }
        }

        companion object : XdrElementDecoder<Pledges> {
            override fun decode(stream: XdrStream): Pledges {
                return when (val type = SCPStatementType.decode(stream)) {
                    SCPStatementType.SCP_ST_PREPARE -> {
                        val prepare = Prepare.decode(stream)
                        StPrepare(prepare)
                    }

                    SCPStatementType.SCP_ST_CONFIRM -> {
                        val confirm = Confirm.decode(stream)
                        StConfirm(confirm)
                    }

                    SCPStatementType.SCP_ST_EXTERNALIZE -> {
                        val externalize = Externalize.decode(stream)
                        StExternalize(externalize)
                    }

                    SCPStatementType.SCP_ST_NOMINATE -> {
                        val nominate = SCPNomination.decode(stream)
                        StNominate(nominate)
                    }
                }
            }
        }
    }

    companion object : XdrElementDecoder<SCPStatement> {
        override fun decode(stream: XdrStream): SCPStatement {
            val nodeID = NodeID.decode(stream)
            val slotIndex = stream.readLong().toULong()
            val pledges = Pledges.decode(stream)
            return SCPStatement(nodeID, slotIndex, pledges)
        }
    }
}