# Copilot Instructions for AI Agents

## Visão Geral do Projeto
Este projeto é uma aplicação Java simples, estruturada para facilitar o desenvolvimento e manutenção de transações financeiras. O código-fonte está localizado em `src/`, enquanto dependências externas ficam em `lib/`. Os arquivos compilados são gerados em `bin/`.

## Fluxo de Trabalho do Desenvolvedor
- **Compilação:** Compile os arquivos Java da pasta `src/` e direcione a saída para `bin/`. Exemplo:
  ```powershell
  javac -d bin src/*.java
  ```
- **Execução:** Execute a aplicação principal (geralmente `App.java`) via:
  ```powershell
  java -cp bin App
  ```
- **Gerenciamento de Dependências:** Adicione arquivos `.jar` em `lib/` e inclua-os no classpath ao compilar/executar:
  ```powershell
  javac -cp "lib/*" -d bin src/*.java
  java -cp "lib/*;bin" App
  ```

## Padrões e Convenções
- **Estrutura:**
  - `src/`: Código-fonte principal
  - `lib/`: Dependências externas (.jar)
  - `bin/`: Saída de compilação
- **Classe Principal:** O ponto de entrada é `App.java`.
- **Sem ferramentas de build:** Não há Maven/Gradle; comandos são manuais.
- **Configuração personalizada:** Ajuste configurações em `.vscode/settings.json` se necessário.

## Integrações e Pontos de Atenção
- **VS Code:** Utilize a visualização `JAVA PROJECTS` para gerenciar dependências.
- **Dependências:** Todos os `.jar` devem estar em `lib/`.
- **Sem testes automatizados:** Não há testes ou scripts de build/teste presentes.

## Exemplos de Comandos
- Compilar: `javac -cp "lib/*" -d bin src/*.java`
- Executar: `java -cp "lib/*;bin" App`

## Recomendações para Agentes
- Priorize comandos Powershell para Windows.
- Documente qualquer convenção ou padrão detectado.
- Atualize este arquivo se novas práticas surgirem.

---

Seções incompletas ou dúvidas? Solicite feedback ao usuário para aprimorar as instruções.