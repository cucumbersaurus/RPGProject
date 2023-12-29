package project.rpg.quests

enum class Quests(
    private val questName: String,
    private val questType: QuestType,
    subQuests: List<Quests>?,
    questId: Int
) {
    NULL("NULL", QuestType.NULL, null, 0),
    DUMMY_QUEST("Dummy Quest for Test", QuestType.NULL, listOf(NULL), 1),
    HERE_WE_GO("마왕 토벌대 모집", QuestType.MAIN, listOf(NULL), 2),
    USING_YOUR_POWERS("소비의 기초", QuestType.MAIN, listOf(NULL), 3),
    OOH_MAGICAL("신비한 액체", QuestType.MAIN, listOf(NULL), 4),
    RICH_AND_POOR("부의 재분배", QuestType.MAIN, listOf(NULL), 5),
    FROSTY_PLAINS("얼어붙은 설원", QuestType.MAIN, listOf(NULL), 5),
    SHARP_EYE("예리한 눈", QuestType.MAIN, listOf(NULL), 6),
    MANSION_OF_SECRETS("저택의 비밀", QuestType.MAIN, listOf(NULL), 7),
    HI_FAIRIES("정려의 숲 페리헴", QuestType.MAIN, listOf(NULL), 8),
    NEGOTIATION("협상", QuestType.MAIN, listOf(NULL), 9),
    I_WANT_THAT("마법의 수정", QuestType.MAIN, listOf(NULL), 10),
    WIZARDLY_VALLEY("마법석 골짜기", QuestType.MAIN, listOf(NULL), 11),
    AND_WE_GO("미지의 포탈", QuestType.MAIN, listOf(NULL), 12),
    SURVIVORS("생존자들", QuestType.MAIN, listOf(NULL), 13),
    RAIDDDD("긴급 수비전", QuestType.MAIN, listOf(NULL), 14),
    COUTNRY_ROADS_TAKE_ME_HOME("다시 고향으로", QuestType.MAIN, listOf(NULL), 15),
    UH_SORRY("징벌적 정의", QuestType.MAIN, listOf(NULL), 16),
    HIDDEN_PASSAGES("숩겨진 통로", QuestType.MAIN, listOf(NULL), 17),
    LETS_GOOO("대륙을 잇는 지하통로", QuestType.MAIN, listOf(NULL), 18),
    RAISZO_THE_PORT("항구마을 라이조", QuestType.MAIN, listOf(NULL), 19),
    BOLB_ATTAQUE("수중 군사기지", QuestType.MAIN, listOf(NULL), 20),
    YOUR_HIGHNESS("황제의 고민", QuestType.MAIN, listOf(NULL), 21),
    NOT_AN_AND_SHIP("하늘의 함선", QuestType.MAIN, listOf(NULL), 22),
    DEFENCE_COLONY("세트룩스 수비", QuestType.MAIN, listOf(NULL), 23),
    SO_WHATS_THAT_PLACE("틈새의 처편", QuestType.MAIN, listOf(NULL), 24);


    val subQuests: ArrayList<Quests> = ArrayList()
    private val questId: Int

    init {
        this.subQuests.addAll(subQuests!!)
        this.questId = questId
    }
}
