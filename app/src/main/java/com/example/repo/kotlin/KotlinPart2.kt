package com.example.repo.kotlin

import android.util.Log
import java.sql.Timestamp
import java.time.LocalTime

/*
  2.

  Создать enum Type с константами DEMO и FULL.
 */
enum class Type {
    DEMO,
    FULL
}

/*
  3.

  Реализовать класс данных User с полями id, name, age и type. У класса User создать ленивое
  свойство startTime, в котором получаем текущее время.
 */
data class User(
    val id: Int,
    val name: String,
    val age: Int,
    val type: Type
) {
    val startTime: LocalTime by lazy { LocalTime.now() }

    //10.
    private val updateCache: () -> Unit = {
        Log.e("UC", "Cash update")
    }

    private inline fun auth(block: () -> Unit, authCallback: AuthCallback ) {

        //11.
        try{
            this.isAdult()

            authCallback.authSuccess()
            block.invoke()
        }catch(e: Exception){
            authCallback.authFailed()
        }
    }

    //13.
    fun doAction(action: Action, authCallback: AuthCallback){
        when(action){
            is Registration->Log.e("ACTION", "Registration started")
            is Login->{
                Log.e("ACTION", "Auth started")

                auth(updateCache, authCallback)
            }
            is Logout->Log.e("ACTION", "Logout started")
        }
    }
}

/*
  4.

  Создать объект класса User, вывести в лог startTime данного юзера, после вызвать
  Thread.sleep(1000) и повторно вывести в лог startTime.
 */

/*
  5.

  Создать список пользователей, содержащий в себе один объект класса User. Используя функцию apply,
  добавить ещё несколько объектов класса User в список пользователей.
 */

/*
  6.

  Получить список пользователей, у которых имеется полный доступ (поле type имеет значение FULL).
 */

/*
  7.

  Преобразовать список User в список имен пользователей. Получить первый и последний элементы
  списка и вывести их в лог.
 */

/*
  8.

  Создать функцию-расширение класса User, которая проверяет, что юзер старше 18 лет, и в случае
  успеха выводит в лог, а в случае неуспеха возвращает ошибку.
 */
fun User.isAdult() {
    if (this.age > 18) {
        Log.e("USER", "is Adult")
    } else {
        throw Exception("Age exception")
    }
}

/*
  9.

  Создать интерфейс AuthCallback с методами authSuccess, authFailed и реализовать анонимный объект
  данного интерфейса. В методах необходимо вывести в лог информацию о статусе авторизации.
 */
interface AuthCallback{
    fun authSuccess()
    fun authFailed()
}

/*
  10.

  Реализовать inline функцию auth, принимающую в качестве параметра функцию updateCache.
  Функция updateCache должна выводить в лог информацию об обновлении кэша.
 */

/*
  11.

  Внутри функции auth вызвать метод коллбека authSuccess и переданный updateCache, если
  проверка возраста пользователя произошла без ошибки. В случае получения ошибки вызвать
  authFailed.
 */

/*
  12.
  Реализовать изолированный класс Action и его наследников – Registration, Login и Logout.
  Login должен принимать в качестве параметра экземпляр класса User.
 */
sealed class Action


class Registration : Action()


class Login(user: User) : Action()


class Logout : Action()

/*
  13.

  Реализовать метод doAction, принимающий экземпляр класса Action. В зависимости от переданного
  действия выводить в лог текст, к примеру “Auth started”. Для действия Login вызывать метод auth.
 */
