package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LevenshteinDistance {
  @EpiTest(testDataFile = "levenshtein_distance.tsv")

  public static int levenshteinDistance(String A, String B) {
    return epi.kashyap.LevensteinDistanceKt.levenshteinDistanceJ(A, B);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
