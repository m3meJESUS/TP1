# TP1 — Documentation abrégée et jeu de tests

Ce document explique rapidement comment compiler et lancer l'application, puis fournit une documentation minimale des routes et un petit jeu de données (un exemple par méthode HTTP) pour tester les principales fonctionnalités.

## 1) Maven (rappel rapide)

- Compiler :

```powershell
mvn compile
```

- Lancer les tests :

```powershell
mvn test
```

- Nettoyer :

```powershell
mvn clean
```

Lancer l'application (classe principale `cal.info.App`):

```powershell
mvn compile; mvn exec:java -Dexec.mainClass="cal.info.App"
```

Le serveur démarre sur `http://localhost:8080`.

## 2) Endpoints essentiels

Base : `http://localhost:8080`

- GET /inventaire — lister toutes les chaussettes
- GET /inventaire?couleur=<val>&taille=<val> — recherche exacte
- POST /inventaire — ajouter une chaussette (JSON body)
- PUT /inventaire — modifier une chaussette (JSON body)
- DELETE /inventaire?id=<id> — supprimer une chaussette

- GET /vente — lister les ventes
- POST /vente — créer une vente (JSON body contenant des chaussettes)
- DELETE /vente?id=<id> — annuler une vente

> Les handlers se trouvent dans `hautLesBas/src/main/java/cal/info/Handlers`.

## 3) Petit jeu de tests (1 exemple par méthode)

Pré-requis : serveur lancé sur `http://localhost:8080`.

- POST /inventaire (ajouter une chaussette) — Body JSON :

```json
{
	"identifiant": 200,
	"couleur": "rose",
	"taille": "M",
	"typeTissu": "coton",
	"prix": 7.0
}
```

- GET /inventaire — vérifier que la chaussette 200 apparaît :

```bash
curl "http://localhost:8080/inventaire"
```

- GET /inventaire?couleur=rose&taille=M — recherche exacte :

```bash
curl "http://localhost:8080/inventaire?couleur=rose&taille=M"
```

- PUT /inventaire (mettre à jour 200) — Body JSON :

```json
{
	"identifiant": 200,
	"couleur": "rose",
	"taille": "M",
	"typeTissu": "laine",
	"prix": 8.5
}
```

- DELETE /inventaire?id=200 — supprimer la chaussette :

```bash
curl -X DELETE "http://localhost:8080/inventaire?id=200"
```

- POST /vente (créer vente qui référence 200 et 201) — Body JSON :

```json
{
	"identifiant": 10,
	"dateVente": "2025-10-13T12:00:00Z",
	"total": 15.0,
	"chaussettes": [
		{ "identifiant": 200, "couleur": "rose", "taille": "M", "typeTissu": "laine", "prix": 8.5 },
		{ "identifiant": 201, "couleur": "gris", "taille": "M", "typeTissu": "coton", "prix": 6.5 }
	]
}
```

## Ajout de vente valide (abrégé)

Étapes minimales :

1. Ajouter les chaussettes référencées (200 et 201) si elles n'existent pas :

```bash
curl -X POST "http://localhost:8080/inventaire" -H "Content-Type: application/json" -d '{"identifiant":200,"couleur":"rose","taille":"M","typeTissu":"laine","prix":8.5}'
curl -X POST "http://localhost:8080/inventaire" -H "Content-Type: application/json" -d '{"identifiant":201,"couleur":"gris","taille":"M","typeTissu":"coton","prix":6.5}'
```

2. Poster la vente :

```bash
curl -X POST "http://localhost:8080/vente" -H "Content-Type: application/json" -d '{"identifiant":10,"dateVente":"2025-10-13T12:00:00Z","total":15.0,"chaussettes":[{"identifiant":200},{"identifiant":201}] }'
```

3. Vérifier :

```bash
curl "http://localhost:8080/vente"
```

Réponse attendue : `Vente ajoutee` puis la vente listée par GET /vente.



