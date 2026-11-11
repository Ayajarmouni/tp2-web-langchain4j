# Utilisation de l'IA

Outil utilisé : Claude (assistant IA d'Anthropic), pour m'aider à avancer sur le TP 2.
J'ai relu, testé et adapté le code obtenu ; les résultats des tests dans l'email viennent de mes
propres exécutions.

## Exemple 1 : erreur 404 sur le nom du modèle (Test 1)

**Prompt :** J'ai collé l'erreur de ma console :
`ModelNotFoundException: 404 NOT_FOUND. This model models/gemini-2.5-flash is no longer available
to new users...`

**Réponse (résumé) :** Le nom de modèle donné dans l'énoncé n'est plus disponible pour les nouveaux
comptes. Il faut changer `.modelName(...)`, par exemple pour `gemini-3.5-flash-lite`.

**Ce que j'ai fait :** J'ai remplacé le nom du modèle dans mon code, relancé le test et obtenu une
réponse.

## Exemple 2 : dépôt Git créé dans le mauvais dossier (partie Web)

**Prompt :** J'ai collé la sortie de `git commit`, qui listait des fichiers de mon dossier
utilisateur (`../../Downloads/...`).

**Réponse (résumé) :** Git travaillait sur le dossier parent. Il fallait créer un dépôt dans le
dossier du projet avec `git init`, vérifier la racine avec `git rev-parse --show-toplevel`, et ne
pas utiliser `git add .` avant.

**Ce que j'ai fait :** J'ai lancé `git init -b main` dans `tp2_web_jarmouniaya`, vérifié avec
`git status` que seuls les fichiers du projet étaient listés, puis fait le commit et le push.