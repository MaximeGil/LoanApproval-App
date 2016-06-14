<?php

namespace loanapproval\Form;

use Silex\Application;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\RepeatedType;

/**
 * Description of FormRegister.
 *
 * @author maxime
 */
class FormAppManager
{
    public function createFormAdd(Application $app)
    {
        $form = $app['form.factory']->createBuilder()
                             ->add('name', TextType::class, array(
                    'constraints' => array(new Assert\NotBlank()), ))
                               ->add('response', TextType::class, array(
                    'constraints' => array(new Assert\NotBlank()), ))
                                ->add('amount', TextType::class, array(
                    'constraints' => array(new Assert\NotBlank()), ))
                  ->getForm();

        return $form;
    }
    
    public function createFormDelete(Application $app)
    {
        $form = $app['form.factory']->createBuilder()
              ->add('name', TextType::class, array(
                    'constraints' => array(new Assert\NotBlank()), ))
                 ->getForm();

        return $form;
    }
}
