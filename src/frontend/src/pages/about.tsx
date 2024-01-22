export function About() {
  return (
    <div>
      <h1>About</h1>

      <div className={"retail-about"}>
        <div className={"retail-about-block"}>
          <h3>RetailAnalytics v2.0 Web</h3>
          <p>Web-интерфейс для проекта SQL3 на языке Java.</p>

          <p>Интерфейс позволяет:</p>
          <ul>
            <li>Авторизоваться под пользователями с разными ролями;</li>
            <li>Делать CRUD операции над всеми таблицами;</li>
            <li>Вычислять персональные предложения по трем категориям;</li>
            <li>Делать импорт во все таблицы базы данных;</li>
            <li>Делать экспорт всех таблиц и результатов персональных предложений.</li>
          </ul>
        </div>

        <div className={"retail-about-block"}>
          <h3>Разработчики</h3>

          <div className={"retail-about-dev-block"}>
            <div>
              <p><b>Катерина (chastity), 14 волна</b></p>

              <p>Роль на проекте:</p>
              <ul>
                <li>Java Backend</li>
              </ul>
            </div>

            <div>
              <p><b>Михаил (rebbecca), 14 волна</b></p>

              <p>Роль на проекте:</p>
              <ul>
                <li>Java Backend</li>
                <li>DevOps (docker/scripts/nginx/flyway)</li>
              </ul>
            </div>

            <div>
              <p><b>Максим (cmichael), 11 волна</b></p>

              <p>Роль на проекте:</p>
              <ul>
                <li>Frontend</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
