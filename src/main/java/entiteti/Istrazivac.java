package entiteti;

public sealed interface Istrazivac permits Lokalitet{
    int countUnos(Lokalitet lok);
}
