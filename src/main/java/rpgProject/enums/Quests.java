package rpgProject.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Quests {

    NULL("NULL", QuestType.NULL, null, 0),
    DUMMY_QUEST("Dummy Quest for Test", QuestType.NULL, Arrays.asList(Quests.NULL), 1),
    HERE_WE_GO("마왕 토벌대 모집", QuestType.MAIN, Arrays.asList(Quests.NULL), 2),
    USING_YOUR_POWERS("소비의 기초", QuestType.MAIN, Arrays.asList(Quests.NULL), 3),
    OOH_MAGICAL("신비한 액체", QuestType.MAIN, Arrays.asList(Quests.NULL), 4),
    RICH_AND_POOR("부의 재분배", QuestType.MAIN, Arrays.asList(Quests.NULL),5 ),
    FROSTY_PLAINS("얼어붙은 설원", QuestType.MAIN, Arrays.asList(Quests.NULL), 5),
    SHARP_EYE("예리한 눈", QuestType.MAIN, Arrays.asList(Quests.NULL), 6),
    MANSION_OF_SECRETS("저택의 비밀", QuestType.MAIN, Arrays.asList(Quests.NULL), 7),
    HI_FAIRIES("정려의 숲 페리헴", QuestType.MAIN, Arrays.asList(Quests.NULL), 8),
    NEGOTIATION("협상", QuestType.MAIN, Arrays.asList(Quests.NULL), 9),
    I_WANT_THAT("마법의 수정", QuestType.MAIN, Arrays.asList(Quests.NULL), 10),
    WIZARDY_VALLEY("마법석 골짜기", QuestType.MAIN, Arrays.asList(Quests.NULL), 11),
    AND_WE_GO("미지의 포탈", QuestType.MAIN, Arrays.asList(Quests.NULL), 12),
    SURVIVORS("생존자들", QuestType.MAIN, Arrays.asList(Quests.NULL), 13),
    RAIDDDD("긴급 수비전", QuestType.MAIN, Arrays.asList(Quests.NULL), 14),
    COUTNRY_ROADS_TAKE_ME_HOME("다시 고향으로", QuestType.MAIN, Arrays.asList(Quests.NULL), 15),
    UH_SORRY("징벌적 정의", QuestType.MAIN, Arrays.asList(Quests.NULL), 16),
    HIDDEN_PASSAGES("숩겨진 통로", QuestType.MAIN, Arrays.asList(Quests.NULL), 17);


    private final String questName;
    private final QuestType questType;
    ArrayList<Quests> subQuests = new ArrayList<>();
    private final int questId;

    Quests(String questName, QuestType questType, List<Quests> subQuests, int questId){
        this.questName = questName;
        this.questType = questType;
        this.subQuests.addAll(subQuests);
        this.questId = questId;
    }

}
