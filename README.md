# Szkeleton
# Killer Sokoban

Egy raktárépületben ládákat tárolnak. A raktárépület padlója négyzetekre van osztva, ezeken állnak a négyzetekkel megegyező alapterületű ládák. A ládák eltolhatók, de egy mozgatással mindig csak egy szomszédos négyzetre kerülhetnek. 

A raktárban többen dolgoznak, a játékosok őket irányítják. A cél a ládák előírt helyre tologatása. A raktárnak falai és oszlopai is vannak, ezeken a ládák nem tolhatók át. A ládák egymást el tudják tolni. Ha egy munkásra ládát tolunk, akkor a munkás automatikusan szomszédos négyzetre tolódik. Ha nem tud eltolódni (mert pl. fal van mellette), a munkás meghal. A ládák nem nyomhatók össze.

A padlón egyes helyeken lyukak találhatók, amelyekre ládát tolva a láda leesik (eltűnik). Ha munkás lép rá, meghal. Némelyik lyuk csak akkor viselkedik lyukként, ha egy kapcsolón láda áll, egyébként padlónak tűnik. A kapcsolón ládának kell állnia, hogy kinyissa a lyukat, ha munkás áll a kapcsolóra, akkor nem kapcsol.

A játék véget ér, ha az összes láda a helyén van, vagy ha már nem lehet többet tolni. Az nyer, aki ekkorra a legtöbb ládát a helyére tolta.
