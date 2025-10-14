<h1 align="center">🪄 MagicFolderCards</h1>

<p align="center">
  <em>CRUD funcional com Jetpack Compose + Room, arquitetura MVVM</em><br><br>
  <img src="https://img.shields.io/badge/Kotlin-2.0-blueviolet?logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/Android%20Studio-Ladybug🐞-3DDC84?logo=androidstudio&logoColor=white" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-%F0%9F%92%8C-blue?logo=jetpackcompose&logoColor=white" />
  <img src="https://img.shields.io/badge/Material%203-%F0%9F%8C%9F-lightgrey" />
</p>

---

📱 **MagicFolderCards** é um app Android em **Kotlin** com **Jetpack Compose** que simula um catálogo de cartas de *Magic: The Gathering*.  
Permite **criar, visualizar, editar, excluir** e alternar **wishlist** de cartas — tudo com **Material 3** e **MVVM**.

---

## 🚀 Funcionalidades

- ✅ Cadastro de cartas (nome, custo de mana, tipo, cores, wishlist)  
- ✅ Listagem com ações: **Editar**, **Excluir** e **Wishlist (toggle)**  
- ✅ Aba **Wishlist** (somente cartas marcadas)  
- ✅ Arquitetura **MVVM** com **Room** (Flow) e **ViewModel** compartilhado  
- ✅ UI moderna em **Jetpack Compose** (bottom bar: Coleção | Wishlist | Nova)

---

## 🧱 Estrutura

```bash
app/src/main/java/com/biarapha/magicfolder/
├── ComposeActivity.kt              # NavHost + bottom bar (Coleção | Wishlist | Nova | Edit)
├── MainActivity.kt                 # Redireciona p/ ComposeActivity
├── data/
│   ├── Card.kt
│   ├── Deck.kt
│   ├── CardDao.kt
│   ├── DeckDao.kt
│   └── MagicFolderDb.kt            # Room DB (v2 + fallback em DEV)
├── ui/
│   ├── CollectionScreen.kt         # Lista (CRUD + toggle wishlist + editar)
│   ├── WishlistScreen.kt           # Somente wishlist
│   ├── NewCardScreen.kt            # Form nova carta
│   └── EditCardScreen.kt           # Form edição (pré-preenchido)
└── vm/
    └── CardViewModel.kt            # ViewModel único (Flow de lista + wishlist)
```

⚙️ Como rodar
Clone:

bash
Copiar código
git clone https://github.com/biavoitechen/MagicFolderCards.git
Abra no Android Studio → aguarde o Gradle Sync.

Launcher na ComposeActivity (AndroidManifest):

xml
Copiar código
<activity android:name=".MainActivity" android:exported="true" />
<activity android:name=".ComposeActivity" android:exported="true">
  <intent-filter>
    <action android:name="android.intent.action.MAIN" />
    <category android:name="android.intent.category.LAUNCHER" />
  </intent-filter>
</activity>
Executar em emulador/API 24+ (ou dispositivo físico).

Se já havia uma versão instalada, desinstale para evitar conflitos de banco.

🧩 Tech stack (principal)
Kotlin, Jetpack Compose, Material 3

Navigation-Compose

Room (Flow, DAO, migração 1→2; fallbackToDestructiveMigration() em DEV)

Lifecycle ViewModel (AndroidViewModel)

🏷 Licença
Uso acadêmico e livre para estudo.
© 2025 — MagicFolderCards
